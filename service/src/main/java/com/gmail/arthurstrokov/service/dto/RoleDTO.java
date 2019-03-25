package com.gmail.arthurstrokov.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class RoleDTO implements Serializable {

    private Long id;
    private String name;
    private Set<PermissionDTO> permissions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(id, roleDTO.id) &&
                Objects.equals(name, roleDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}