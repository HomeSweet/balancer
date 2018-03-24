package it.discovery.order.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerConfiguration;
import it.discovery.balancer.impl.DefaultBalancerAPI;
import it.discovery.balancer.impl.strategy.NoneHealthCheckStrategy;
import it.discovery.balancer.impl.strategy.RandomServerSelectionStrategy;

@Configuration
public class ClientBalancerConfig {
	
	@Bean
	public DefaultBalancerAPI balancerAPI(
			ServerConfiguration serverConfiguration,
			HealthCheckStrategy healthCheckStrategy,
			ServerSelectionStrategy serverSelectionStrategy) {
		return new DefaultBalancerAPI(
				serverConfiguration, serverSelectionStrategy,
				healthCheckStrategy);
	}
	
	@Bean
	public HealthCheckStrategy healthCheckStrategy() {
		return new NoneHealthCheckStrategy();
	}
	
	@Bean
	public ServerSelectionStrategy serverSelectionStrategy(
			ServerConfiguration serverConfiguration) {
		return new RandomServerSelectionStrategy(serverConfiguration.getServers());
	}
	
	@Bean
	@ConfigurationProperties("balancer")
	public ServerConfiguration serverConfiguration() {
		return new ServerConfiguration();
	}

}
