package es.codeurjc.externalConsumer.repositories;

import es.codeurjc.externalConsumer.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id);

}
