package br.com.cleilsonandrade.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Product result = repository.findById(id).get();
    return new ProductDTO(result);
  }

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAll(Pageable pageable) {
    Page<Product> result = repository.findAll(pageable);
    return result.map(x -> new ProductDTO(x));
  }
}
