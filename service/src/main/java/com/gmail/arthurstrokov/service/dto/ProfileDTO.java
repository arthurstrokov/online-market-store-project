package com.gmail.arthurstrokov.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class ProfileDTO implements Serializable {

    private Long userId;
    private String address;
    private String phone;
    private UserDTO user;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
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
        ProfileDTO that = (ProfileDTO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, address, phone);
    }
}
