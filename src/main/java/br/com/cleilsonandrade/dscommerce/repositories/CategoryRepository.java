package br.com.cleilsonandrade.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cleilsonandrade.dscommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
