package com.example.product_catalog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private Double price;

  private Integer availability;

  private String category;

  public ProductDTO() {
    // default implementation ignored

  }

  public Long getId() {

    return this.id;
  }

  public void setId(Long id) {

    this.id = id;
  }

  public String getName() {

    return this.name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public String getDescription() {

    return this.description;
  }

  public void setDescription(String description) {

    this.description = description;
  }

  public Double getPrice() {

    return this.price;
  }

  public void setPrice(Double price) {

    this.price = price;
  }

  public Integer getAvailability() {

    return this.availability;
  }

  public void setAvailability(Integer availability) {

    this.availability = availability;
  }

  public String getCategory() {

    return this.category;
  }

  public void setCategory(String category) {

    this.category = category;
  }
}
