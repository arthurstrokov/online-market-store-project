package com.gmail.arthurstrokov.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
public class AuditDTO implements Serializable {

    private Long id;
    private String eventType;
    private LocalDateTime created;
    private AuditUserDTO user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditDTO auditDTO = (AuditDTO) o;
        return Objects.equals(id, auditDTO.id) &&
                Objects.equals(eventType, auditDTO.eventType) &&
                Objects.equals(created, auditDTO.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventType, created);
    }
}