package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Person;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Person user = userMapper.userDtoToPerson(userDto);
        log.info("Mapped user: {}", user);
        Person savedUser = userRepository.save(user);
        log.info("Saved user: {}", savedUser);
        return userMapper.personToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        // реализовать недстающие методы
        Person user = userMapper.userDtoToPerson(userDto);
        log.info("Mapped user: {}", user);

        userRepository.findById(user.getId()).orElseThrow(
                () -> new NotFoundException("User with id " + userDto.getId() + " is not found"));

        Person updatedUser = userRepository.save(user);
        log.info("Updated user: {}", updatedUser);
        return userMapper.personToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        // реализовать недстающие методы
        log.info("Got user with id: {}", id);
        Person gotUser = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id " + id + " is not found"));
        return userMapper.personToUserDto(gotUser);
    }

    @Override
    public void deleteUserById(Long id) {
        // реализовать недстающие методы
        log.info("Person id to delete: {}", id);
        userRepository.deleteById(id);
        log.info("Person with id {} is deleted", id);
    }

}
