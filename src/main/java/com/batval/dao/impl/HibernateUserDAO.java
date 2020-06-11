package com.batval.dao.impl;

import com.batval.dao.UserDAO;
import com.batval.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateUserDAO implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.openSession();
    }


    @Override
    public List<User> getAll() {
        return getCurrentSession().createQuery("from User",User.class).list();
    }

    @Override
    public User getOne(String email) {
        Query<User> q = getCurrentSession().createQuery(
                "from User where email = :email", User.class);
        q.setParameter("email", email);
        return q.list().stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        getCurrentSession().save(user);
    }
}
