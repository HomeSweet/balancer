package it.discovery.balancer.cache;

import java.time.Duration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import it.discovery.balancer.config.ServerConfiguration;
import it.discovery.balancer.config.ServerConfiguration.CacheConfiguration;

public class CaffeineCacheStrategy implements CacheStrategy {

	private final Cache<String, Object> cache;
	
	public CaffeineCacheStrategy(ServerConfiguration 
			serverConfiguration) {
		CacheConfiguration cacheConfiguration = serverConfiguration
				.getCacheConfiguration();
		cache = Caffeine.newBuilder()
				.expireAfterAccess(Duration.ofMillis(
						cacheConfiguration.getExpirationTime()))
				.maximumSize(cacheConfiguration.getMaxSize())
				.initialCapacity(cacheConfiguration.getInitialSize())
				.build();
	}

	@Override
	public <T> T get(String key) {
		return (T) cache.getIfPresent(key);
	}

	@Override
	public <T> void update(String key, T t) {
		cache.put(key, t);
	}
}
