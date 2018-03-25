package it.discovery.balancer.config;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Validated
public class ServerConfiguration {
	
	@Valid
	private List<ServerDefinition> servers;
	
	@Valid
	private RetryConfiguration retryConfiguration;
	
	@Valid
	private CacheConfiguration cacheConfiguration;
	
	@Getter @Setter @Validated
	public static class RetryConfiguration {
		/**
		 * Delay in milliseconds
		 */
		@Min(10)
		private int delay;
		
		@Max(10)
		private int maxRetries;
	}
	
	@Getter @Setter @Validated
	public static class CacheConfiguration {
		/**
		 * Whether cache globally enabled or disabled
		 */
		private boolean enabled;
		@Min(1)
		private int maxSize;
		
		@Min(1)
		private int initialSize;
		
		@Min(1000)
		/**
		 * Expiration time after the last access
		 */
		private int expirationTime;
	}
}
