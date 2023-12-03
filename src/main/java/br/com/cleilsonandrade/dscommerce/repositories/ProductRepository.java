package br.com.cleilsonandrade.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cleilsonandrade.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
