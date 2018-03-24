package it.discovery.balancer.api;

import java.util.List;

import it.discovery.balancer.config.ServerConfiguration;
import it.discovery.balancer.config.ServerDefinition;

public interface BalancerAPI {
	
	void setServerConfiguration(ServerConfiguration serverConfiguration);
	
	ServerConfiguration getServerConfiguration();
	
	void setServerSelectionStrategy(ServerSelectionStrategy strategy);
	
	ServerSelectionStrategy getServerSelectionStrategy();
	
	void setHealthCheckStrategy(HealthCheckStrategy strategy);
	
	HealthCheckStrategy getHealthCheckStrategy();
}
