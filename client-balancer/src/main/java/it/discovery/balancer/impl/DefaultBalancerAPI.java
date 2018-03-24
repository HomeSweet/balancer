package it.discovery.balancer.impl;

import java.util.List;

import it.discovery.balancer.api.BalancerAPI;
import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerConfiguration;
import it.discovery.balancer.config.ServerDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class DefaultBalancerAPI implements BalancerAPI{

	private ServerConfiguration serverConfiguration;
	
	private ServerSelectionStrategy serverSelectionStrategy;
	
	private HealthCheckStrategy healthCheckStrategy;
}
