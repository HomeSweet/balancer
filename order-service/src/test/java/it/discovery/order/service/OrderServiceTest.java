package it.discovery.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import it.discovery.order.OrderApplication;
import it.discovery.order.model.Book;
import it.discovery.order.model.Order;

@SpringJUnitWebConfig(OrderApplication.class)
@SpringBootTest
@JsonTest
@AutoConfigureWebClient
@ComponentScan("it.discovery")
public class OrderServiceTest {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JacksonTester<Book> bookTester;

	@Autowired
	private OrderService orderService;

	private MockRestServiceServer mockRestServiceServer;
	
	@Autowired
	private Environment env;

	@BeforeEach
	public void setup() {
		mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void makeOrder_validBookId_OrderIsCreated() throws IOException {
		Book book = new Book();
		book.setId(1);
		book.setPrice(1000);
		
		mockRestServiceServer.expect(requestTo(env
				.getProperty("app.book-service.url") + "/book/1"))
		.andRespond(withSuccess(bookTester.write(book)
				.getJson(), MediaType.APPLICATION_JSON_UTF8));
		
		Order order = orderService.makeOrder(1);
		mockRestServiceServer.verify();
		
		assertEquals(order.getBookId(), book.getId());
		assertEquals(order.getPrice(), book.getPrice());
		

	}

}
