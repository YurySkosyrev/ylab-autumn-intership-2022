package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dao.impl.BookDaoImpl;
import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.dao.BookDaoMapper;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookDaoImpl bookDao;
    private final BookDaoMapper bookDaoMapper;

    public BookServiceImpl(BookDaoImpl bookDao,
                           BookDaoMapper bookDaoMapper) {
        this.bookDao = bookDao;
        this.bookDaoMapper = bookDaoMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book createdBook = bookDao.createBook(bookDaoMapper.bookDtoToBook(bookDto));
        return bookDaoMapper.bookToBookDto(createdBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book updatedBook = bookDao.updateBook(bookDaoMapper.bookDtoToBook(bookDto));
        return bookDaoMapper.bookToBookDto(updatedBook);
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookDaoMapper.bookToBookDto(bookDao.getBookById(id));
    }

    @Override
    public void deleteBookById(Long id) {
        bookDao.deleteBookById(id);
    }
}
