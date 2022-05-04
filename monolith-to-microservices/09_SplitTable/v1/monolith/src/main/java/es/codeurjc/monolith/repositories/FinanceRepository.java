package es.codeurjc.monolith.repositories;

import es.codeurjc.monolith.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinanceRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Integer id);

}
