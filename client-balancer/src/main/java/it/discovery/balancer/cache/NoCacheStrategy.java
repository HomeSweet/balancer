package it.discovery.balancer.cache;

public class NoCacheStrategy implements CacheStrategy{

	@Override
	public <T> T get(String key) {
		return null;
	}

	@Override
	public <T> void update(String key, T t) {
	}

}
