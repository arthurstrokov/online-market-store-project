package com.gmail.arthurstrokov.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UpdateUserRoleDTO implements Serializable {

    private Long userId;
    private Long roleId;
}