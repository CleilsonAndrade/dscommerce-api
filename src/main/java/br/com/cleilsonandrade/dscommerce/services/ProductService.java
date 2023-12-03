package br.com.cleilsonandrade.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleilsonandrade.dscommerce.dto.ProductDTO;
import br.com.cleilsonandrade.dscommerce.entities.Product;
import br.com.cleilsonandrade.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
    Optional<Product> result = repository.findById(id);
    Product product = result.get();
    ProductDTO dto = new ProductDTO(product);
    return dto;
  }
}
