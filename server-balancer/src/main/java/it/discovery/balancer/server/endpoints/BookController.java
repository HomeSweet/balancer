package it.discovery.balancer.server.endpoints;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.discovery.balancer.api.BalancerAPI;
import it.discovery.balancer.server.model.Book;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

	private final BalancerAPI balancerAPI;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Book> getBooks() {
		return balancerAPI.getRestService()
				.getForObject("/book", List.class);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> findBookById(@PathVariable int id) {
		return balancerAPI.getRestService()
				.getForObject("/book/" + id, ResponseEntity.class);
	}

}
