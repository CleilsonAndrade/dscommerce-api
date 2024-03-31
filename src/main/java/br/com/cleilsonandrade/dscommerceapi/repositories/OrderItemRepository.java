package br.com.cleilsonandrade.dscommerceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cleilsonandrade.dscommerceapi.entities.OrderItem;
import br.com.cleilsonandrade.dscommerceapi.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
