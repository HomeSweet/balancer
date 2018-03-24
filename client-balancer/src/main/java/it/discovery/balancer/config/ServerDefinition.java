package it.discovery.balancer.config;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Validated 
public class ServerDefinition {
	@NotBlank
	private String url;
	
	private boolean enabled;
}
