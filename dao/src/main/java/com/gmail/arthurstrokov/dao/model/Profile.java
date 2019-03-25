package com.gmail.arthurstrokov.dao.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "userId=" + userId +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", user=" + user +
                '}';
    }

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
