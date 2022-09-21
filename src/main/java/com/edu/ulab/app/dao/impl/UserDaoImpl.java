package com.edu.ulab.app.dao.impl;

import com.edu.ulab.app.dao.UserDao;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {


    private final Storage storage;

    public UserDaoImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public User createUser(User user) {
        return storage.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        if (user.getId() == null || storage.getUserById(user.getId()) == null){
            storage.createUser(user);
        } else{
            User existingUser  = storage.getUserById(user.getId());
            if(user.getAge() == 0){
                user.setAge(existingUser.getAge());
            }
            if(user.getTitle() == null){
                user.setTitle(existingUser.getTitle());
            }
            if(user.getFullName() == null){
                user.setFullName(existingUser.getFullName());
            }
            user.setBooks(existingUser.getBooks());
        }
        return storage.updateUser(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = storage.getUserById(id);
        if(user == null){
            throw  new NotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<Long> getUserBooksId(Long id) {
        User user = storage.getUserById(id);
        if(user == null){
            throw  new NotFoundException("User not found");
        }
        return user.getBooks();
    }

    @Override
    public void deleteUserById(Long id) {
        storage.deleteUser(id);
    }
}
