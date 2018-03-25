package it.discovery.balancer.impl.rest;

import java.net.URI;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.discovery.balancer.api.RestService;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.event.MetricEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestTemplateService extends RestTemplate 
implements RestService {
	private final ServerSelectionStrategy serverSelectionStrategy;
	
	private final ApplicationEventPublisher publisher;

	@Override
	protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor) throws RestClientException {
		try {
		String prefix = 
				serverSelectionStrategy.select().getUrl(); 
		return super.doExecute(URI.create(
				prefix + url.toString()), method, requestCallback, responseExtractor);
		} finally {
			publisher.publishEvent(new MetricEvent(this, 
					"balancer.total.requests", 1));
		}
	}
}
