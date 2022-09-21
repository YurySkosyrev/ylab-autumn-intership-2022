package com.edu.ulab.app.mapper;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.web.request.BookRequest;
import com.edu.ulab.app.web.request.UpdateBookRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateBookMapper {

    BookDto updateBookRequestToBookDto(UpdateBookRequest updateBookRequest);

    UpdateBookRequest bookDtoToUpdateBookRequest(BookDto bookDto);
}
