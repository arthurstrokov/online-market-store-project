package com.gmail.arthurstrokov.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity(name = "Profile")
@Table(name = "T_PROFILE")
public class Profile implements Serializable {

    @GenericGenerator(
            name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long userId;
    @Column(name = "ADDRESS")
    @Size(max = 50)
    private String address;
    @Column(name = "PHONE")
    @Size(max = 12)
    private String phone;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @NotNull
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(userId, profile.userId) &&
                Objects.equals(address, profile.address) &&
                Objects.equals(phone, profile.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, address, phone);
    }
}