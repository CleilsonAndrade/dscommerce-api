package br.com.cleilsonandrade.dscommerceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cleilsonandrade.dscommerceapi.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
