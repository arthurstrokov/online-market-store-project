package com.gmail.arthurstrokov.dao;

import com.gmail.arthurstrokov.dao.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    User validateByEmail(String email);

    void deleteById(long entityId);

    List<User> getAllUsersForPage(Long page);

    Long countAllUsers();
}