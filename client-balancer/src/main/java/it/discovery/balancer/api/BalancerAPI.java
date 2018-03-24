package it.discovery.balancer.api;

import org.springframework.web.client.RestOperations;

import it.discovery.balancer.config.ServerConfiguration;

public interface BalancerAPI {
	
	void setServerConfiguration(ServerConfiguration serverConfiguration);
	
	ServerConfiguration getServerConfiguration();
	
	void setServerSelectionStrategy(ServerSelectionStrategy strategy);
	
	ServerSelectionStrategy getServerSelectionStrategy();
	
	void setHealthCheckStrategy(HealthCheckStrategy strategy);
	
	HealthCheckStrategy getHealthCheckStrategy();
	
	void setRestService(RestOperations restOperations);
	
	RestOperations getRestService();
}
