package es.codeurjc.customer.repositories;

import java.util.Optional;

import es.codeurjc.customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Integer id);

}
