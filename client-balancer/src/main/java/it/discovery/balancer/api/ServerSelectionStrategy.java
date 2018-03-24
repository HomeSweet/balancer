package it.discovery.balancer.api;

import it.discovery.balancer.config.ServerDefinition;

public interface ServerSelectionStrategy {
	
	ServerDefinition select();

}
