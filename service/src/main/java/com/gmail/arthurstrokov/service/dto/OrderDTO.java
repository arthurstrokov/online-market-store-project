package com.gmail.arthurstrokov.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class OrderDTO implements Serializable {

    private Long id;
    private LocalDateTime created;
    private Integer quantity;
    private String status;
    private UserDTO user;
    private ItemDTO item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", created=" + created +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", item=" + item +
                '}';
    }

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
