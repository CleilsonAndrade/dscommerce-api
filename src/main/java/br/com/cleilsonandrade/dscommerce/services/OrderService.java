package br.com.cleilsonandrade.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleilsonandrade.dscommerce.dto.OrderDTO;
import br.com.cleilsonandrade.dscommerce.dto.OrderItemDTO;
import br.com.cleilsonandrade.dscommerce.entities.Order;
import br.com.cleilsonandrade.dscommerce.entities.OrderItem;
import br.com.cleilsonandrade.dscommerce.entities.OrderStatus;
import br.com.cleilsonandrade.dscommerce.entities.Product;
import br.com.cleilsonandrade.dscommerce.entities.User;
import br.com.cleilsonandrade.dscommerce.repositories.OrderItemRepository;
import br.com.cleilsonandrade.dscommerce.repositories.OrderRepository;
import br.com.cleilsonandrade.dscommerce.repositories.ProductRepository;
import br.com.cleilsonandrade.dscommerce.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
  @Autowired
  private OrderRepository repository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private OrderItemRepository orderItemRepository;

  @Transactional(readOnly = true)
  public OrderDTO findById(Long Id) {
    Order order = repository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

    return new OrderDTO(order);
  }

  @Transactional
  public OrderDTO insert(OrderDTO dto) {
    Order order = new Order();

    order.setMoment(Instant.now());
    order.setStatus(OrderStatus.WAITING_PAYMENT);

    User user = userService.authenticated();
    order.setClient(user);

    for (OrderItemDTO itemDto : dto.getItems()) {
      Product product = productRepository.getReferenceById(itemDto.getProductId());
      OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
      order.getItems().add(item);
    }

    repository.save(order);
    orderItemRepository.saveAll(order.getItems());
    return new OrderDTO(order);
  }
}
