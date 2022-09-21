package com.edu.ulab.app.mapper;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.web.request.UpdateBookRequest;
import com.edu.ulab.app.web.request.UpdateUserRequest;
import com.edu.ulab.app.web.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateUserMapper {
    UserDto updateUserRequestToUserDto(UpdateUserRequest updateUserRequest);

    UpdateUserRequest userDtoToUpdateUserRequest(UserDto userDto);
}
