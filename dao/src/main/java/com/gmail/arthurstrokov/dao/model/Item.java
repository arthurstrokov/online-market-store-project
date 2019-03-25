package com.gmail.arthurstrokov.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "Item")
@Table(name = "T_ITEM")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "NAME")
    @NotNull
    @Size(max = 50)
    private String name;
    @Column(name = "DESCRIPTION")
    @Type(type = "text")
    @NotNull
    private String description;
    @Column(name = "UNIQUE_NUMBER")
    @Size(max = 23)
    @NotNull
    private String uniqueNumber;
    @Column(name = "PRICE")
    @NotNull
    private BigDecimal price;
    @Column(name = "IS_ALIVE")
    @NotNull
    private Boolean isAlive;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                Objects.equals(uniqueNumber, item.uniqueNumber) &&
                Objects.equals(price, item.price) &&
                Objects.equals(isAlive, item.isAlive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, uniqueNumber, price, isAlive);
    }
}