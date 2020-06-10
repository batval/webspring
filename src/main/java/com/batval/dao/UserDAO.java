package com.batval.dao;

import com.batval.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll();

    User getOne(String email);

    void addUser(User user);
}
