package it.discovery.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.impl.DefaultBalancerAPI;
import it.discovery.balancer.impl.strategy.NoneHealthCheckStrategy;
import it.discovery.balancer.impl.strategy.RandomServerSelectionStrategy;

@Configuration
public class ClientBalancerConfig {
	
	@Bean
	public DefaultBalancerAPI balancerAPI(
			HealthCheckStrategy healthCheckStrategy,
			ServerSelectionStrategy serverSelectionStrategy) {
		return new DefaultBalancerAPI();
	}
	
	@Bean
	public HealthCheckStrategy healthCheckStrategy() {
		return new NoneHealthCheckStrategy();
	}
	
	public ServerSelectionStrategy serverSelectionStrategy() {
		//TODO
		return new RandomServerSelectionStrategy(null);
	}

}
