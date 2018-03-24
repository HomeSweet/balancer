package it.discovery.order.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.discovery.order.model.Book;
import it.discovery.order.model.Order;
import it.discovery.order.repository.OrderRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	@Value("${app.book-service.url}")
	private String bookServiceUrl;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public void makeOrder(int bookId) {
		Order order = new Order();
		order.setBookId(bookId);
		order.setCreated(LocalDateTime.now());
		Book book = restTemplate.getForObject(
				bookServiceUrl + "/book", Book.class);
		order.setPrice(book.getPrice());
		orderRepository.save(order);
	}
}