package es.codeurjc.inventoryservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.codeurjc.inventoryservice.domain.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,UUID>{

	Optional<Inventory> findByReference(@Param("reference") String reference);
}
