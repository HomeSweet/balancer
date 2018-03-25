package it.discovery.balancer.config;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.event.EventListener;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import it.discovery.balancer.event.MetricEvent;

public class BalancerMetricsConfig {
	private final Map<String, Counter> metrics = 
			new HashMap<>();
	public BalancerMetricsConfig(ServerConfiguration configuration, MeterRegistry meterRegistry) {
		metrics.put("balancer.average.request.time", meterRegistry
				.counter("balancer.average.request.time"));
		metrics.put("balancer.total.requests",
				meterRegistry.counter("balancer.total.requests"));
		configuration.getServers().forEach(server -> {
			URI uri = URI.create(server.getUrl());
			String metricName = "balancer.requests." + 
					uri.getHost() + "." + uri.getPort();
			metrics.put(metricName, 
					meterRegistry.counter(metricName));
		});
	}
	
	@EventListener
	public void handleEvent(MetricEvent event) {
		metrics.get(event.getName()).increment(event.getValue());
	}
}
