package com.codegym.form.service;

import com.codegym.form.model.User;

public interface UserService {
    Iterable<User> findAll();

    void save(User user);

    User findById(int id);

    void remove(int id);
}
