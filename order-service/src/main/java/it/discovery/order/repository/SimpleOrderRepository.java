package it.discovery.order.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import it.discovery.order.model.Order;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SimpleOrderRepository implements OrderRepository {
	private final Map<Integer, Order> orders = new HashMap<>();

	private int counter = 0;

	@Override
	public Order findById(int id) {
		return orders.get(id);
	}

	@Override
	public List<Order> findAll() {
		return new ArrayList<>(orders.values());
	}

	@Override
	public void save(Order order) {
		if (order.getId() == 0) {
			counter++;
			order.setId(counter);
			orders.put(counter, order);
			log.debug("*** Order with id={} was created", order.getId());
		} else {
			orders.put(order.getId(), order);
			log.debug("*** Order with id={} was updated", order.getId());
		}
	}

	@Override
	public boolean delete(int id) {
		if (!orders.containsKey(id)) {
			return false;
		}

		orders.remove(id);
		System.out.println("*** Order with id=" + id + " was deleted");
		return true;
	}

	@Override
	public boolean isEmpty() {
		return orders.isEmpty();
	}

}
