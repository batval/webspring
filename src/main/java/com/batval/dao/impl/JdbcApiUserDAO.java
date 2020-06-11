package com.batval.dao.impl;

import com.batval.dao.UserDAO;
import com.batval.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class JdbcApiUserDAO implements UserDAO {

    private static Connection connection;

    static {
        String url = null;
        String username = null;
        String password = null;
        //load db properties
        try (InputStream in = UserDAO.class.getClassLoader().getResourceAsStream("persistence.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //acquire db connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        try {
            List<User> users = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("select * from users");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString(1));
                user.setSurname(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                users.add(user);
            }
            return users;
        } catch (SQLException ignored) {

        }
        return null;
    }

    @Override
    public User getOne(String email) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where email=?");
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString(1));
                user.setSurname(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                return user;
            }

        } catch (SQLException ignored) {
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into users values(?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.execute();
        } catch (SQLException ignored) {
        }
    }
}
