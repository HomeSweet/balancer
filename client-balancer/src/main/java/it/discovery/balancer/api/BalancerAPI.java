package it.discovery.balancer.api;

import java.util.List;

import it.discovery.balancer.config.ServerDefinition;

public interface BalancerAPI {
	
	void setServers(List<ServerDefinition> servers);
	
	List<ServerDefinition> getServers();
	
	void setServerSelectionStrategy(ServerSelectionStrategy strategy);
	
	ServerSelectionStrategy getServerSelectionStrategy();
	
	void setHealthCheckStrategy(HealthCheckStrategy strategy);
	
	HealthCheckStrategy getHealthCheckStrategy();
}
