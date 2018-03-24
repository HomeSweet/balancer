package it.discovery.balancer.api;

import it.discovery.balancer.config.ServerDefinition;

public interface HealthCheckStrategy {
	
	void healthCheck(ServerDefinition serverDefinition);

}
