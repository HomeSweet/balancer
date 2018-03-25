package it.discovery.balancer.config.spring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.micrometer.core.instrument.MeterRegistry;
import it.discovery.balancer.api.HealthCheckStrategy;
import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.cache.CacheStrategy;
import it.discovery.balancer.cache.NoCacheStrategy;
import it.discovery.balancer.config.BalancerMetricsConfig;
import it.discovery.balancer.config.ServerConfiguration;
import it.discovery.balancer.impl.rest.RestTemplateService;
import it.discovery.balancer.impl.strategy.NoneHealthCheckStrategy;
import it.discovery.balancer.impl.strategy.RandomServerSelectionStrategy;

@Configuration
@EnableScheduling
public class ClientBalancerAutoConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	public HealthCheckStrategy healthCheckStrategy() {
		return new NoneHealthCheckStrategy();
	}
	
	@Bean
	@ConditionalOnMissingBean
	public CacheStrategy cacheStrategy() {
		return new NoCacheStrategy();
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
			ServerSelectionStrategy strategy,
			ApplicationEventPublisher publisher,
			ServerConfiguration serverConfiguration,
			CacheStrategy cacheStrategy) {
		return new RestTemplateService(strategy,
				publisher, serverConfiguration, cacheStrategy);
	}
	
	@Bean
	@ConditionalOnMissingBean
	public BalancerMetricsConfig balancerMetricsConfig(
			ServerConfiguration serverConfiguration,
			MeterRegistry meterRegistry) {
		return new BalancerMetricsConfig(serverConfiguration,
				meterRegistry);
	}

}
