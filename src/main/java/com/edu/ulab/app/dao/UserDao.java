package com.edu.ulab.app.dao;

import com.edu.ulab.app.entity.User;

import java.util.List;

public interface UserDao {

    User createUser(User user);

    User updateUser(User user);

    User getUserById(Long id);

    List<Long> getUserBooksId(Long id);

    void deleteUserById(Long id);
}
