package it.discovery.balancer.api;

import org.springframework.web.client.RestOperations;

public interface BalancerAPI {
	RestOperations getRestService();
}
