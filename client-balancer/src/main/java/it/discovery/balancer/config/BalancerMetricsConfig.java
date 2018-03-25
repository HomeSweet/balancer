package it.discovery.balancer.config;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class BalancerMetricsConfig {
	private final Counter averageRequestTime;

	private final Counter totalRequests;

	private final List<Counter> serverRequests = 
			new ArrayList<>();

	public BalancerMetricsConfig(ServerConfiguration configuration, MeterRegistry meterRegistry) {
		averageRequestTime = meterRegistry.counter("balancer.average.request.time");
		totalRequests = meterRegistry.counter("balancer.total.requests");
		configuration.getServers().forEach(server -> {
			URI uri = URI.create(server.getUrl());
			serverRequests.add(
			meterRegistry.counter("balancer.requests." + uri.getHost() + "." + uri.getPort()));
		});
	}
}
