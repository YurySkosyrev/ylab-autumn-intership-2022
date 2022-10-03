package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Person;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImplTemplate implements UserService {
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public UserServiceImplTemplate(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }
    @Override
    public UserDto createUser(UserDto userDto) {

        final String INSERT_SQL = "INSERT INTO PERSON(FULL_NAME, TITLE, AGE) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                    ps.setString(1, userDto.getFullName());
                    ps.setString(2, userDto.getTitle());
                    ps.setLong(3, userDto.getAge());
                    return ps;
                }, keyHolder);

        userDto.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        // реализовать недстающие методы
        final String UPDATE_SQL = "UPDATE PERSON SET FULL_NAME=?, TITLE=?, AGE=? WHERE ID=?";

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(UPDATE_SQL, new String[]{"id"});
                    ps.setString(1, userDto.getFullName());
                    ps.setString(2, userDto.getTitle());
                    ps.setLong(3, userDto.getAge());
                    ps.setLong(4, userDto.getId());
                    return ps;
                });
        log.info("Updated user: {}", userDto);
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {

        final String SELECT_SQL = "SELECT * FROM PERSON WHERE ID=?";

        Person gotUser = jdbcTemplate.query(
                        connection -> {
                            PreparedStatement ps = connection.prepareStatement(SELECT_SQL, new String[]{"id"});
                            ps.setLong(1, id);
                            return ps;
                        },
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElseThrow(
                        () -> new NotFoundException("User with id " + id + "is not found"));

        return userMapper.personToUserDto(gotUser);

    }

    @Override
    public void deleteUserById(Long id) {
        // реализовать недстающие методы
        final String DELETE_SQL = "DELETE FROM PERSON WHERE ID=?";

        log.info("Person id to delete: {}", id);
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(DELETE_SQL, new String[]{"id"});
                    ps.setLong(1, id);
                    return ps;
                });
        log.info("Person with id {} deleted", id);
    }
}
