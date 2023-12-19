package br.com.cleilsonandrade.dscommerce.dto;

import br.com.cleilsonandrade.dscommerce.entities.OrderItem;

public class OrderItemDTO {
  private Long productId;
  private String name;
  private Double price;
  private Integer quantity;
  private String imgUrl;

  public OrderItemDTO(Long productId, String name, Double price, Integer quantity, String imgUrl) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.imgUrl = imgUrl;
  }

  public OrderItemDTO(OrderItem entity) {
    productId = entity.getProduct().getId();
    name = entity.getProduct().getName();
    price = entity.getPrice();
    quantity = entity.getQuantity();
    imgUrl = entity.getProduct().getImgUrl();
  }

  public Long getProductId() {
    return productId;
  }

  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Double getSubTotal() {
    return price * quantity;
  }

  public String getImgUrl() {
    return imgUrl;
  }

}
