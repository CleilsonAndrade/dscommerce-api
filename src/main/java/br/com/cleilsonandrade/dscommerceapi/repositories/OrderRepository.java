package br.com.cleilsonandrade.dscommerceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cleilsonandrade.dscommerceapi.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
