package it.discovery.balancer;

import java.util.List;

import it.discovery.balancer.config.ServerDefinition;

public interface BalancerAPI {
	
	void setServers(List<ServerDefinition> servers);
	
	List<ServerDefinition> getServers();
	
	void setServerSelectionStrategy(ServerSelectionStrategy strategy);
	
	ServerSelectionStrategy getServerSelectionStrategy();
	
	void setHealthCheckingStrategy(HealthCheckStrategy strategy);
	
	HealthCheckStrategy getHealthCheckStrategy();
}
