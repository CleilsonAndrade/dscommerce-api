package br.com.cleilsonandrade.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleilsonandrade.dscommerce.dto.OrderDTO;
import br.com.cleilsonandrade.dscommerce.entities.Order;
import br.com.cleilsonandrade.dscommerce.repositories.OrderRepository;
import br.com.cleilsonandrade.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
  @Autowired
  private OrderRepository repository;

  @Transactional(readOnly = true)
  public OrderDTO findById(Long Id) {
    Order order = repository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

    return new OrderDTO(order);

  }
}
