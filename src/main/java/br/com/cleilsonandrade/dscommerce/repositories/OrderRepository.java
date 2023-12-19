package br.com.cleilsonandrade.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cleilsonandrade.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
