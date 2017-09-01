package com.avenuecode.orders.repository;

import com.avenuecode.orders.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable> {

	public List<Order> findByStatus(String status);

	@Query("SELECT o FROM Order o WHERE o.discount != 0")
	public List<Order> findByDiscount();

}
