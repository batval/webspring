package com.batval.dao.impl;

import com.batval.dao.UserDAO;
import com.batval.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class JdbcTemplateUserDAO implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getOne(String email) {
        return jdbcTemplate.query("select * from users where email=?", new Object[]{email}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        jdbcTemplate.update("insert into users values(?,?,?)", user.getName(), user.getSurname(), user.getEmail());
    }

}
