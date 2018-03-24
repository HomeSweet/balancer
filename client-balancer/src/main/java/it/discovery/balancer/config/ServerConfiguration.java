package it.discovery.balancer.config;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Validated
public class ServerConfiguration {
	
	@Valid
	private List<ServerDefinition> servers;
}
