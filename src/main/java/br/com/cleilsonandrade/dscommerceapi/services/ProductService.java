package br.com.cleilsonandrade.dscommerceapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleilsonandrade.dscommerceapi.dto.CategoryDTO;
import br.com.cleilsonandrade.dscommerceapi.dto.ProductDTO;
import br.com.cleilsonandrade.dscommerceapi.dto.ProductMinDTO;
import br.com.cleilsonandrade.dscommerceapi.entities.Category;
import br.com.cleilsonandrade.dscommerceapi.entities.Product;
import br.com.cleilsonandrade.dscommerceapi.repositories.ProductRepository;
import br.com.cleilsonandrade.dscommerceapi.services.exceptions.DatabaseException;
import br.com.cleilsonandrade.dscommerceapi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {
    Product result = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    return new ProductDTO(result);
  }

  @Transactional(readOnly = true)
  public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
    Page<Product> result = repository.searchByName(name, pageable);
    return result.map(x -> new ProductMinDTO(x));
  }

  @Transactional
  public ProductDTO insert(ProductDTO dto) {
    Product entity = new Product();
    copyDtoToEntity(dto, entity);
    entity = repository.save(entity);

    return new ProductDTO(entity);
  }

  @Transactional
  public ProductDTO update(Long id, ProductDTO dto) {
    try {
      Product entity = repository.getReferenceById(id);
      copyDtoToEntity(dto, entity);
      entity = repository.save(entity);
      return new ProductDTO(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Resource not found");
    }
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Resource not found");
    }

    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Recurso nao encontrado");
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Referential integrity failure");
    }
  }

  private void copyDtoToEntity(ProductDTO dto, Product entity) {
    entity.setName(dto.getName());
    entity.setDescription(dto.getDescription());
    entity.setPrice(dto.getPrice());
    entity.setImgUrl(dto.getImgUrl());

    entity.getCategories().clear();
    for (CategoryDTO catDto : dto.getCategories()) {
      Category cat = new Category();
      cat.setId(catDto.getId());
      entity.getCategories().add(cat);
    }
  }
}
