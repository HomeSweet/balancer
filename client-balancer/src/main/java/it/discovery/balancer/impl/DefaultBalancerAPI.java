package it.discovery.balancer.impl;

import org.springframework.web.client.RestOperations;

import it.discovery.balancer.api.BalancerAPI;
import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class DefaultBalancerAPI implements BalancerAPI{

	private ServerConfiguration serverConfiguration;
	
	private ServerSelectionStrategy serverSelectionStrategy;
	
	private HealthCheckStrategy healthCheckStrategy;
	
	private RestOperations restService;
}
