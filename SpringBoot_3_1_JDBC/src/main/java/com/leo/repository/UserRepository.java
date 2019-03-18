package com.leo.repository;

import com.leo.model.User;

import java.util.List;

public interface UserRepository {
    int save(User user);
    int update(User user);
    int delete(long id);
    List<User> findAll();
    User findById(long id);
}
