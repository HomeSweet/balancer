package it.discovery.balancer.impl.rest;

import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import it.discovery.balancer.api.RestService;
import it.discovery.balancer.api.ServerSelectionStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestTemplateService extends RestTemplate 
implements RestService {
	private final ServerSelectionStrategy serverSelectionStrategy; 

	@Override
	protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor) throws RestClientException {
		String prefix = 
				serverSelectionStrategy.select().getUrl(); 
		return super.doExecute(URI.create(
				prefix + url.toString()), method, requestCallback, responseExtractor);
	}
	
	

}
