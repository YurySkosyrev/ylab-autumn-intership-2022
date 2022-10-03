package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.Person;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.repository.BookRepository;
import com.edu.ulab.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookMapper.bookDtoToBook(bookDto);
        log.info("Mapped book: {}", book);
        Book savedBook = bookRepository.save(book);
        log.info("Saved book: {}", savedBook);
        return bookMapper.bookToBookDto(savedBook);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {

        Book book = bookMapper.bookDtoToBook(bookDto);
        log.info("Book to update: {}", book);
        Book updatedBook = bookRepository.save(book);
        log.info("Saved book: {}", updatedBook);
        return bookMapper.bookToBookDto(updatedBook);
    }

    @Override
    public BookDto getBookById(Long id) {
        // реализовать недстающие методы
        log.info("Book id to find: {}", id);
        Book foundBook = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book with id " + id + "is not found"));

        log.info("Found book: {}", foundBook);
        return bookMapper.bookToBookDto(foundBook);
    }

    public List<Long> getBooksIdByPersonId(Long id){
        List<Long> booksId = bookRepository.findByUserId(id)
                .stream()
                .filter(Objects::nonNull)
                .map(Book::getId)
                .toList();
        return booksId;
    }

    @Override
    public void deleteBookById(Long id) {
        // реализовать недстающие методы
        log.info("Book id to delete: {}", id);

        Book deletedBook = bookRepository.findById(id).orElseThrow(
                () ->  new NotFoundException("Book with id " + id + " is not found"));

        log.info("Deleted book: {}", deletedBook);
        bookRepository.delete(deletedBook);
    }

}
