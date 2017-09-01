package com.avenuecode.orders.test.utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.domain.Product;

public class MockOrdersForTest {

	public Order getSingleOrder() {
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product("2", "1358743283", "7394650110003",
				"Polo Shirt", new BigDecimal(19.99));
		Product product2 = new Product("3", "1458843283", "7394750120000",
				"Floral Swing Skirt", new BigDecimal(69.99));
		Product product3 = new Product("5", "1258793283", "7394950140000",
				"True Skinny Jeans", new BigDecimal(49.99));
		products.add(product1);
		products.add(product2);
		products.add(product3);
		Order order = new Order("3", "RTL_1003", new BigDecimal(19.99),
				new BigDecimal(8.5), new BigDecimal(139.97), new BigDecimal(
						11.89), new BigDecimal(131.87), "SHIPPED", products);
		return order;
	}

	public List<Order> getOrderByStatus() {
		List<Order> orderList = new ArrayList<Order>();
		List<Product> products1 = new ArrayList<Product>();
		Product product1 = new Product("1", "1257833283", "9394550220002",
				"Diva Jeans", new BigDecimal(39.99));
		Product product2 = new Product("2", "1358743283", "7394650110003",
				"Polo Shirt", new BigDecimal(19.99));
		products1.add(product1);
		products1.add(product2);
		Order order1 = new Order("1", "RTL_1001", new BigDecimal(0),
				new BigDecimal(10), new BigDecimal(59.98),
				new BigDecimal(5.99), new BigDecimal(65.97), "SHIPPED",
				products1);
		List<Product> products2 = new ArrayList<Product>();
		Product product2_1 = new Product("1", "1257833283", "9394550220002",
				"Diva Jeans", new BigDecimal(39.99));
		Product product2_2 = new Product("3", "1458843283", "7394750120000",
				"Floral Swing Skirt", new BigDecimal(69.99));
		products2.add(product2_1);
		products2.add(product2_2);
		Order order4 = new Order("4", "RTL_1004", new BigDecimal(0),
				new BigDecimal(10), new BigDecimal(109.98), new BigDecimal(
						10.99), new BigDecimal(120.97), "SHIPPED", products2);
		orderList.add(order1);
		orderList.add(getSingleOrder());
		orderList.add(order4);
		return orderList;
	}

	public List<Order> getOrderByDiscount() {
		List<Order> orderList = new ArrayList<Order>();
		List<Product> products2 = new ArrayList<Product>();
		Product product2_1 = new Product("2", "1358743283", "7394650110003",
				"Polo Shirt", new BigDecimal(19.99));
		products2.add(product2_1);
		Order order2 = new Order("2", "RTL_1002", new BigDecimal(15.55),
				new BigDecimal(10), new BigDecimal(19.99),
				new BigDecimal(1.99), new BigDecimal(6.43), "FULFILLED",
				products2);
		orderList.add(order2);
		orderList.add(getSingleOrder());
		return orderList;
	}

	public List<Order> getOrderMoreThanTwoProducts() {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(getSingleOrder());
		return orderList;
	}

	public List<Order> getAllOrders() {
		List<Order> orderList = new ArrayList<Order>();
		List<Product> products1 = new ArrayList<Product>();
		Product product1 = new Product("1", "1257833283", "9394550220002",
				"Diva Jeans", new BigDecimal(39.99));
		Product product2 = new Product("2", "1358743283", "7394650110003",
				"Polo Shirt", new BigDecimal(19.99));
		products1.add(product1);
		products1.add(product2);
		Order order1 = new Order("1", "RTL_1001", new BigDecimal(0),
				new BigDecimal(10), new BigDecimal(59.98),
				new BigDecimal(5.99), new BigDecimal(65.97), "SHIPPED",
				products1);

		List<Product> products2 = new ArrayList<Product>();
		Product product2_1 = new Product("2", "1358743283", "7394650110003",
				"Polo Shirt", new BigDecimal(19.99));
		products2.add(product2_1);
		Order order2 = new Order("2", "RTL_1002", new BigDecimal(15.55),
				new BigDecimal(10), new BigDecimal(19.99),
				new BigDecimal(1.99), new BigDecimal(6.43), "FULFILLED",
				products2);

		List<Product> products4 = new ArrayList<Product>();
		Product product4_1 = new Product("1", "1257833283", "9394550220002",
				"Diva Jeans", new BigDecimal(39.99));
		Product product4_2 = new Product("3", "1458843283", "7394750120000",
				"Floral Swing Skirt", new BigDecimal(69.99));
		products4.add(product4_1);
		products4.add(product4_2);
		Order order4 = new Order("4", "RTL_1004", new BigDecimal(0),
				new BigDecimal(10), new BigDecimal(109.98), new BigDecimal(
						10.99), new BigDecimal(120.97), "SHIPPED", products4);

		List<Product> products5 = new ArrayList<Product>();
		Product product5_1 = new Product("5", "1258793283", "7394950140000",
				"True Skinny Jeans", new BigDecimal(49.99));
		;
		products5.add(product5_1);
		Order order5 = new Order("5", "RTL_1005", new BigDecimal(0),
				new BigDecimal(9.5), new BigDecimal(49.99),
				new BigDecimal(4.74), new BigDecimal(54.73), "FULFILLED",
				products5);

		orderList.add(order1);
		orderList.add(order2);
		orderList.add(getSingleOrder());
		orderList.add(order4);
		orderList.add(order5);

		return orderList;
	}

}
