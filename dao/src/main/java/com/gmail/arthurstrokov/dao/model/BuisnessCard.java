package com.gmail.arthurstrokov.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity(name = "BuisnessCard")
@Table(name = "T_BUISNESS_CARD")
public class BuisnessCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false, updatable = false, nullable = false)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "WORKING_TELEPHONE")
    private String workingTelephone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuisnessCard that = (BuisnessCard) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(workingTelephone, that.workingTelephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, fullName, workingTelephone);
    }
}