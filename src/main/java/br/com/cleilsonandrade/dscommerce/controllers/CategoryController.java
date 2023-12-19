package br.com.cleilsonandrade.dscommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleilsonandrade.dscommerce.dto.CategoryDTO;
import br.com.cleilsonandrade.dscommerce.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> findAll() {
    List<CategoryDTO> list = categoryService.findAll();
    return ResponseEntity.ok(list);
  }

}
