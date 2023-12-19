package br.com.cleilsonandrade.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cleilsonandrade.dscommerce.entities.OrderItem;
import br.com.cleilsonandrade.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
