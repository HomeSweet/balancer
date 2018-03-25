package it.discovery.order.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestOperations;

import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerConfiguration;
import it.discovery.balancer.config.spring.ClientBalancerAutoConfiguration;
import it.discovery.balancer.impl.DefaultBalancerAPI;
import it.discovery.balancer.impl.strategy.ActuatorHealthCheckStrategy;

@Configuration
@Import(ClientBalancerAutoConfiguration.class)
public class ClientBalancerConfig {
	
	@Bean
	public DefaultBalancerAPI balancerAPI(
			ServerConfiguration serverConfiguration,
			HealthCheckStrategy healthCheckStrategy,
			ServerSelectionStrategy serverSelectionStrategy,
			RestOperations restTemplate) {
		return new DefaultBalancerAPI(
				serverConfiguration, serverSelectionStrategy,
				healthCheckStrategy, restTemplate);
	}
	
	@Bean
	public HealthCheckStrategy healthCheckStrategy() {
		return new ActuatorHealthCheckStrategy();
	}
	
	@Bean
	@ConfigurationProperties("balancer")
	public ServerConfiguration serverConfiguration() {
		return new ServerConfiguration();
	}
}
