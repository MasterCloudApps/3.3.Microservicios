package es.codeurjc.customerservice.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.codeurjc.customerservice.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}
