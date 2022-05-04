package es.codeurjc.loyalty.repositories;

import es.codeurjc.loyalty.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Integer id);

}
