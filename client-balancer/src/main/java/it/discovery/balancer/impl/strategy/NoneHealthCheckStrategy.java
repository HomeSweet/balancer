package it.discovery.balancer.impl.strategy;

import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.config.ServerDefinition;

public class NoneHealthCheckStrategy implements HealthCheckStrategy {

	@Override
	public void healthCheck(ServerDefinition serverDefinition) {
		System.out.println("Health checking server " + serverDefinition.getUrl());
	}

}
