package com.gmail.arthurstrokov.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ItemDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String uniqueNumber;
    private BigDecimal price;
    private Boolean isAlive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uniqueNumber='" + uniqueNumber + '\'' +
                ", price=" + price +
                ", isAlive=" + isAlive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return Objects.equals(id, itemDTO.id) &&
                Objects.equals(name, itemDTO.name) &&
                Objects.equals(description, itemDTO.description) &&
                Objects.equals(uniqueNumber, itemDTO.uniqueNumber) &&
                Objects.equals(price, itemDTO.price) &&
                Objects.equals(isAlive, itemDTO.isAlive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, uniqueNumber, price, isAlive);
    }
}
