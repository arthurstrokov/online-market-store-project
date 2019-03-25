package com.gmail.arthurstrokov.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity(name = "Order")
@Table(name = "T_ORDER")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false, updatable = false, nullable = false)
    private Long id;
    @Column(name = "CREATED")
    private LocalDateTime created;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    @NotNull
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(created, order.created) &&
                Objects.equals(quantity, order.quantity) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, quantity, status);
    }
}