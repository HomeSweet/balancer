package it.discovery.order.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.discovery.order.model.Book;
import it.discovery.order.model.Order;
import it.discovery.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	private final RestTemplate restTemplate;
	
	public void makeOrder(int bookId) {
		Order order = new Order();
		order.setBookId(bookId);
		order.setCreated(LocalDateTime.now());
		Book book = restTemplate.getForObject(
				"/book/" + bookId, Book.class);
		order.setPrice(book.getPrice());
		orderRepository.save(order);
	}
}
