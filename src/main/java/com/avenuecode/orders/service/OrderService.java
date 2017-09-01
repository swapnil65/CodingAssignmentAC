package com.avenuecode.orders.service;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> listOrders() {
		return orderRepository.findAll();
	}

	public Order getOrder(String orderId) {
		return orderRepository.findOne(orderId);
	}

	public List<Order> searchOrders(String criteria) {

		List<Order> searchResults = null;
		switch (criteria) {
		case "shipped":
			searchResults = orderRepository.findByStatus("SHIPPED");
			break;
		case "discountApplied":
			searchResults = orderRepository.findByDiscount();
			break;
		case "moreThanTwoProducts":
			List<Order> allOrders = orderRepository.findAll();
			searchResults = new ArrayList<Order>();
			for (Order order : allOrders) {
				if (order.getProducts().size() > 2)
					searchResults.add(order);
			}
			break;
		}
		return searchResults;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	// public List<Order> searchOrdersByDiscount() {
	// List<Order> searchResults = null;
	// searchResults = orderRepository.findByDiscount();
	// return searchResults;
	//
	// }
	//
	// public List<Order> searchOrdersByProducts() {
	// List<Order> searchResults = new ArrayList<Order>();
	// List<Order> allOrders = orderRepository.findAll();
	// for(Order order : allOrders) {
	// if(order.getProducts().size() > 2)
	// searchResults.add(order);
	// }
	// return searchResults;
	//
	// }

}
