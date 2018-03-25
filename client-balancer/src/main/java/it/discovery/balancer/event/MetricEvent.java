package it.discovery.balancer.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class MetricEvent extends ApplicationEvent{
	private static final long serialVersionUID = 6341429748025236701L;
	
	public MetricEvent(Object source, String name,
			double value) {
		super(source);
		this.name = name;
		this.value = value;
	}

	private final String name;
	
	private final double value;
}
