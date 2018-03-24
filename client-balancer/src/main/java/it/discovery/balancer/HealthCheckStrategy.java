package it.discovery.balancer;

import it.discovery.balancer.config.ServerDefinition;

public interface HealthCheckStrategy {
	
	void healthCheck(ServerDefinition serverDefinition);

}
