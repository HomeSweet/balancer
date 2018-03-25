package it.discovery.balancer.impl;

import org.springframework.web.client.RestOperations;

import it.discovery.balancer.api.BalancerAPI;
import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DefaultBalancerAPI implements BalancerAPI{

	private final ServerConfiguration serverConfiguration;
	
	private final ServerSelectionStrategy serverSelectionStrategy;
	
	private final HealthCheckStrategy healthCheckStrategy;
	
	private final RestOperations restService;
}
