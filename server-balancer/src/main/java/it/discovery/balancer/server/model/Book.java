package it.discovery.balancer.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter @ToString
public class Book {
	private int id;

	private String author;

	private String name;
	
	private int year;
	
	private double price;

}
