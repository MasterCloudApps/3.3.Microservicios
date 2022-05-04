package es.codeurjc.shop.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.shop.orders.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
