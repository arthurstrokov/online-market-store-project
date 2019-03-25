package com.gmail.arthurstrokov.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ItemDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String uniqueNumber;
    private BigDecimal price;
    private Boolean isAlive;

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