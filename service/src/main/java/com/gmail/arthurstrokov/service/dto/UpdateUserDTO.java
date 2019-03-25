package com.gmail.arthurstrokov.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserDTO {

    private Long id;
    private String email;
    private String surname;
    private String name;
    private String password;
    private String address;
    private String phone;
}