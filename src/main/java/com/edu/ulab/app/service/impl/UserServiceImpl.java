package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dao.impl.UserDaoImpl;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.mapper.dao.UserDaoMapper;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;
    private final UserDaoMapper userDaoMapper;

    public UserServiceImpl(UserDaoImpl userDao,
                           UserDaoMapper userDtoMapper) {
        this.userDao = userDao;
        this.userDaoMapper = userDtoMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User createdUser = userDao.createUser(userDaoMapper.userDtoToUser(userDto));
        return userDaoMapper.userToUserDto(createdUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User updatedUser = userDao.updateUser(userDaoMapper.userDtoToUser(userDto));
        return userDaoMapper.userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userDaoMapper.userToUserDto(userDao.getUserById(id));
    }

    @Override
    public List<Long> getUserBooksId(Long id) {
        return userDao.getUserBooksId(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }
}
