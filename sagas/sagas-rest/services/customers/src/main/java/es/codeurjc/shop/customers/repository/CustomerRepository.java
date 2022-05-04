package es.codeurjc.shop.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.shop.customers.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
