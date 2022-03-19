package com.gopi.rest.webservices.restfulwebservices.user.dao;

import com.gopi.rest.webservices.restfulwebservices.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList<>();

    private int currentCount = 3;

    static {
        users.add(new User(1, "gopi", new Date()));
        users.add(new User(2, "Dhanu", new Date()));
        users.add(new User(3, "Rohit", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++currentCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User delete(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User nextUser = iterator.next();
            if (nextUser.getId() == id) {
                iterator.remove();
                return nextUser;
            }
        }
        return null;
    }
}
