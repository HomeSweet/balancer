package it.discovery.order.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.discovery.order.service.OrderService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping("/{bookId}")
	public void makeOrder(@PathVariable int bookId) {
		orderService.makeOrder(bookId);
	}

}
