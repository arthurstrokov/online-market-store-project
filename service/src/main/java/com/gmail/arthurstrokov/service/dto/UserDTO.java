package com.gmail.arthurstrokov.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;
    private String email;
    private String surname;
    private String name;
    private String password;
    private Boolean status;
    private RoleDTO role;
    private ProfileDTO profile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(surname, userDTO.surname) &&
                Objects.equals(name, userDTO.name) &&
                Objects.equals(password, userDTO.password) &&
                Objects.equals(status, userDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, surname, name, password, status);
    }
}