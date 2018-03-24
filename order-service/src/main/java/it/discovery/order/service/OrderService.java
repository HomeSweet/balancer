package it.discovery.order.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import it.discovery.balancer.api.BalancerAPI;
import it.discovery.order.model.Book;
import it.discovery.order.model.Order;
import it.discovery.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	private final BalancerAPI balancerAPI;
	
	public Order makeOrder(int bookId) {
		Order order = new Order();
		order.setBookId(bookId);
		order.setCreated(LocalDateTime.now());
		Book book = balancerAPI.getRestService().getForObject(
				"/book/" + bookId, Book.class);
		order.setPrice(book.getPrice());
		orderRepository.save(order);
		
		return order;
	}
}
