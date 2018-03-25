package it.discovery.balancer.impl.rest;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.discovery.balancer.api.RestService;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.cache.CacheStrategy;
import it.discovery.balancer.config.ServerConfiguration;
import it.discovery.balancer.config.ServerConfiguration.RetryConfiguration;
import it.discovery.balancer.event.MetricEvent;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

public class RestTemplateService extends RestTemplate implements RestService {
	private final ServerSelectionStrategy serverSelectionStrategy;

	private final ApplicationEventPublisher publisher;
	
	private final CacheStrategy cacheStrategy;

	private final RetryPolicy retryPolicy;

	public RestTemplateService(ServerSelectionStrategy serverSelectionStrategy, ApplicationEventPublisher publisher,
			ServerConfiguration serverConfiguration,
			CacheStrategy cacheStrategy) {
		this.serverSelectionStrategy = serverSelectionStrategy;
		this.publisher = publisher;
		this.cacheStrategy = cacheStrategy;
		RetryConfiguration retryConfiguration = serverConfiguration.getRetryConfiguration();

		retryPolicy = new RetryPolicy()
				.withDelay(retryConfiguration.getDelay(), TimeUnit.MILLISECONDS)
				.retryOn(ResourceAccessException.class)
				.withMaxRetries(retryConfiguration.getMaxRetries());
	}

	@Override
	protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor) throws RestClientException {
		T t = cacheStrategy.get(url.toString());
		if(t != null) {
			return t;
		}
		
		t = Failsafe.with(retryPolicy).onFailedAttempt((ex) ->
				System.out.println("Connection error: " + 
		ex.getMessage()))
				.get(() -> {
			try {
				String prefix = serverSelectionStrategy.select().getUrl();
				return super.doExecute(URI.create(prefix + url.toString()), method, requestCallback, responseExtractor);
			} finally {
				publisher.publishEvent(new MetricEvent(this, "balancer.total.requests", 1));
			}
		});
		cacheStrategy.update(url.toString(), t);
		return t;
	}

}
