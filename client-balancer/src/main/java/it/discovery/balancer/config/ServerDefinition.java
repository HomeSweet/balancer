package it.discovery.balancer.config;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServerDefinition {
	private String url;
	
	private boolean enabled;
}
