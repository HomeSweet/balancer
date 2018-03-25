package it.discovery.balancer.config.spring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerConfiguration;
import it.discovery.balancer.impl.rest.RestTemplateService;
import it.discovery.balancer.impl.strategy.NoneHealthCheckStrategy;
import it.discovery.balancer.impl.strategy.RandomServerSelectionStrategy;

@Configuration
public class ClientBalancerAutoConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	public HealthCheckStrategy healthCheckStrategy() {
		return new NoneHealthCheckStrategy();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public ServerSelectionStrategy serverSelectionStrategy(
			ServerConfiguration serverConfiguration) {
		return new RandomServerSelectionStrategy(serverConfiguration.getServers());
	}
	
	@Bean
	@ConditionalOnMissingBean
	public RestTemplateService restTemplate(
			ServerSelectionStrategy strategy) {
		return new RestTemplateService(strategy);
	}

}
