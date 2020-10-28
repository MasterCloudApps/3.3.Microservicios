package es.codeurjc.shop.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.shop.products.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
