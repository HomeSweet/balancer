package it.discovery.balancer.impl.strategy;

import java.util.List;
import java.util.Random;

import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerDefinition;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RandomServerSelectionStrategy implements
ServerSelectionStrategy{
	
	private final List<ServerDefinition> servers;
	
	private Random random = new Random();

	@Override
	public ServerDefinition select() {
		return servers.get(random.nextInt(servers.size()));
	}

}
