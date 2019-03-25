package com.gmail.arthurstrokov.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "BuisnessCard")
@Table(name = "T_BUISNESS_CARD")
public class BuisnessCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", insertable = false, updatable = false, nullable = false)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "WORKING_TELEPHONE")
    private String workingTelephone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkingTelephone() {
        return workingTelephone;
    }

    public void setWorkingTelephone(String workingTelephone) {
        this.workingTelephone = workingTelephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BuisnessCard{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fullName='" + fullName + '\'' +
                ", workingTelephone='" + workingTelephone + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuisnessCard that = (BuisnessCard) o;
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
