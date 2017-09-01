package com.avenuecode.orders.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.repository.OrderRepository;
import com.avenuecode.orders.service.OrderService;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestOrdersService {

	private OrderService orderService;
	private OrderRepository orderRepositoryMock;
	private static final String orderId = "3";
	private static final String orderStatus = "SHIPPED";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		orderService = new OrderService();
		orderRepositoryMock = mock(OrderRepository.class);
		orderService.setOrderRepository(orderRepositoryMock);
	}

	@Test
	public void checkListOrders() {
		List<Order> expected = new ArrayList<Order>();
		when(orderRepositoryMock.findAll()).thenReturn(expected);
		List<Order> actual = orderService.listOrders();
		verify(orderRepositoryMock, times(1)).findAll();
		verifyNoMoreInteractions(orderRepositoryMock);
		assertEquals(expected, actual);
	}

	@Test
	public void checkGetOrder() {
		Order expected = new Order();
		when(orderRepositoryMock.findOne(orderId)).thenReturn(expected);
		Order actual = orderService.getOrder(orderId);
		verify(orderRepositoryMock, times(1)).findOne(orderId);
		verifyNoMoreInteractions(orderRepositoryMock);
		assertEquals(expected, actual);
	}

	@Test
	public void searchOrdersByStatus() {
		List<Order> expected = new ArrayList<Order>();
		when(orderRepositoryMock.findByStatus(orderStatus))
				.thenReturn(expected);
		List<Order> actual = orderService.searchOrders("shipped");
		verify(orderRepositoryMock, times(1)).findByStatus(orderStatus);
		verifyNoMoreInteractions(orderRepositoryMock);
		assertEquals(expected, actual);
	}

	@Test
	public void searchOrdersByDiscount() {
		List<Order> expected = new ArrayList<Order>();
		when(orderRepositoryMock.findByDiscount()).thenReturn(expected);
		List<Order> actual = orderService.searchOrders("discountApplied");
		verify(orderRepositoryMock, times(1)).findByDiscount();
		verifyNoMoreInteractions(orderRepositoryMock);
		assertEquals(expected, actual);
	}

	@Test
	public void searchOrdersByProducts() {
		List<Order> expected = new ArrayList<Order>();
		List<Order> temp = new ArrayList<Order>();
		temp.add(orderRepositoryMock.findOne(orderId));
		when(temp).thenReturn(expected);
		List<Order> actual = orderService.searchOrders("discountApplied");
		assertEquals(expected, actual);
	}

}
