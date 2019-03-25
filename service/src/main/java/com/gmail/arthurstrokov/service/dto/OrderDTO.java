package com.gmail.arthurstrokov.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
public class OrderDTO implements Serializable {

    private Long id;
    private LocalDateTime created;
    private Integer quantity;
    private String status;
    private UserDTO user;
    private ItemDTO item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) &&
                Objects.equals(created, orderDTO.created) &&
                Objects.equals(quantity, orderDTO.quantity) &&
                Objects.equals(status, orderDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, quantity, status);
    }
}