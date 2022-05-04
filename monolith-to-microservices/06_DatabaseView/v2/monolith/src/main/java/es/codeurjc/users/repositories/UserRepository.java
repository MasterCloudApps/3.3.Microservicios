package es.codeurjc.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.users.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Integer id);

}
