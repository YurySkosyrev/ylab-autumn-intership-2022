package com.edu.ulab.app.mapper.dao;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDaoMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
