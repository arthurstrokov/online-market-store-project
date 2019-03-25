package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.User;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.AuditUserDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("auditUserEntityConverter")
public class AuditUserConverter implements EntityConverter<User, AuditUserDTO> {

    @Override
    public User toEntity(AuditUserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        return user;
    }

    @Override
    public List<User> toEntityList(List<AuditUserDTO> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
