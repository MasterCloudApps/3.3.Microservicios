package es.codeurjc.loyalty.repositories;

import es.codeurjc.loyalty.models.LoyaltyView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoyaltyRepository extends JpaRepository<LoyaltyView, Long> {

    Optional<LoyaltyView> findById(Integer id);

}
