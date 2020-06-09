package com.batval.dao;

import com.batval.model.User;
import com.mysql.cj.protocol.Resultset;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class UserDAO {

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

    public List<User> getAll() throws SQLException {
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
    }


}
