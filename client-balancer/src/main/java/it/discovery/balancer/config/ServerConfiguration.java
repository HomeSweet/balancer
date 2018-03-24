package it.discovery.balancer.config;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServerConfiguration {
	
	private List<ServerDefinition> servers;
}
