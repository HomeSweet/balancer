package it.discovery.balancer.impl.rest;

import java.net.ConnectException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Retry;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.discovery.balancer.api.RestService;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerConfiguration;
import it.discovery.balancer.config.ServerConfiguration.RetryConfiguration;
import it.discovery.balancer.event.MetricEvent;
import lombok.RequiredArgsConstructor;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

public class RestTemplateService extends RestTemplate implements RestService {
	private final ServerSelectionStrategy serverSelectionStrategy;

	private final ApplicationEventPublisher publisher;

	private final RetryConfiguration retryConfiguration;

	private final RetryPolicy retryPolicy;

	public RestTemplateService(ServerSelectionStrategy serverSelectionStrategy, ApplicationEventPublisher publisher,
			ServerConfiguration serverConfiguration) {
		this.serverSelectionStrategy = serverSelectionStrategy;
		this.publisher = publisher;
		this.retryConfiguration = serverConfiguration.getRetryConfiguration();

		retryPolicy = new RetryPolicy().withDelay(retryConfiguration.getDelay(), TimeUnit.MILLISECONDS)
				.retryOn(ConnectException.class).withMaxRetries(retryConfiguration.getMaxRetries());
	}

	@Override
	protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor) throws RestClientException {
		T t = Failsafe.with(retryPolicy).get(() -> {
			try {
				String prefix = serverSelectionStrategy.select().getUrl();
				return super.doExecute(URI.create(prefix + url.toString()), method, requestCallback, responseExtractor);
			} finally {
				publisher.publishEvent(new MetricEvent(this, "balancer.total.requests", 1));
			}
		});
		return t;
	}

}
