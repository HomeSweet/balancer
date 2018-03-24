package it.discovery.balancer.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import it.discovery.balancer.api.BalancerAPI;
import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerDefinition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class DefaultBalancerAPI implements BalancerAPI{

	@Value("${balancer.servers}")
	private List<ServerDefinition> servers;
	
	private ServerSelectionStrategy serverSelectionStrategy;
	
	private HealthCheckStrategy healthCheckStrategy;
}
