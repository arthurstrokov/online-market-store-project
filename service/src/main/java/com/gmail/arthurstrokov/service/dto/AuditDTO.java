package com.gmail.arthurstrokov.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AuditDTO implements Serializable {

    private Long id;
    private String eventType;
    private LocalDateTime created;
    private AuditUserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public AuditUserDTO getUser() {
        return user;
    }

    public void setUser(AuditUserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuditDTO{" +
                "id=" + id +
                ", eventType='" + eventType + '\'' +
                ", created=" + created +
                ", user=" + user +
                '}';
    }

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
