package it.discovery.balancer;

import it.discovery.balancer.config.ServerDefinition;

public interface ServerSelectionStrategy {
	
	ServerDefinition select();

}
