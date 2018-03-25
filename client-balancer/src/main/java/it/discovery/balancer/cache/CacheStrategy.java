package it.discovery.balancer.cache;

public interface CacheStrategy {

	<T> T get(String key);
	
	<T> void update(String key, T t);

}
