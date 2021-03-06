package it.discovery.balancer.impl.strategy;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import it.discovery.balancer.api.ServerSelectionStrategy;
import it.discovery.balancer.config.ServerDefinition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor @Slf4j
public class RandomServerSelectionStrategy implements
ServerSelectionStrategy{
	
	private final List<ServerDefinition> servers;
	
	private Random random = new Random();

	@Override
	public ServerDefinition select() {
		int idx = random.nextInt(servers.size());
		log.debug("Randomly selected server {}",
				servers.get(idx).getUrl());
		return servers.get(idx);
	}
	
}
