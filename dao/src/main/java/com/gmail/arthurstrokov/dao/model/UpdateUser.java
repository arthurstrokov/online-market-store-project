package com.gmail.arthurstrokov.dao.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUser {

    private Long id;
    private String email;
    private String surname;
    private String name;
    private String password;
    private String address;
    private String phone;
}