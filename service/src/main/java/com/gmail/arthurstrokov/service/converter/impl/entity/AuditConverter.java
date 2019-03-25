package com.gmail.arthurstrokov.service.converter.impl.entity;

import com.gmail.arthurstrokov.dao.model.Audit;
import com.gmail.arthurstrokov.service.converter.EntityConverter;
import com.gmail.arthurstrokov.service.dto.AuditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("auditEntityConverter")
public class AuditConverter implements EntityConverter<Audit, AuditDTO> {

    private final AuditUserConverter auditUserEntityConverter;

    @Autowired
    public AuditConverter(
            @Qualifier("auditUserEntityConverter") AuditUserConverter auditUserEntityConverter
    ) {
        this.auditUserEntityConverter = auditUserEntityConverter;
    }

    @Override
    public Audit toEntity(AuditDTO dto) {
        Audit audit = new Audit();
        audit.setId(dto.getId());
        audit.setEventType(dto.getEventType());
        audit.setCreated(dto.getCreated());
        audit.setUser(auditUserEntityConverter.toEntity(dto.getUser()));
        return audit;
    }

    @Override
    public List<Audit> toEntityList(List<AuditDTO> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
