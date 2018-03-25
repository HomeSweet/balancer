package it.discovery.balancer.impl.strategy;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.config.ServerDefinition;

public class ActuatorHealthCheckStrategy implements
HealthCheckStrategy{
	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public void healthCheck(ServerDefinition serverDefinition) {
		Map responseEntity= 
				restTemplate.getForObject(serverDefinition.getUrl() + 
				"/actuator/health", Map.class);
		System.out.println(responseEntity);
	}

}
