package it.discovery.balancer.impl.strategy;

import java.security.SecureRandom;
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
		int idx = random.nextInt(servers.size());
		System.out.println("Randomly selected server " +
		servers.get(idx).getUrl());
		return servers.get(idx);
	}
	
	public static void main(String[] args) {
		Random random = new SecureRandom();
		for (int i = 0; i < 15; i++) {
			System.out.println(random.nextInt(2));	
		}
		
	}

}
