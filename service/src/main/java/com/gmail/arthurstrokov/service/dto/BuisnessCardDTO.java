package com.gmail.arthurstrokov.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class BuisnessCardDTO {

    private Long id;
    private String title;
    private String fullName;
    private String workingTelephone;
    private UserDTO user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuisnessCardDTO that = (BuisnessCardDTO) o;
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