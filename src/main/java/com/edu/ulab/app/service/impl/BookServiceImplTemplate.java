package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.service.BookService;
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
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BookServiceImplTemplate implements BookService {

    private final JdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper;

    public BookServiceImplTemplate(JdbcTemplate jdbcTemplate, BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        final String INSERT_SQL = "INSERT INTO BOOK(TITLE, AUTHOR, PAGE_COUNT, USER_ID) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                        ps.setString(1, bookDto.getTitle());
                        ps.setString(2, bookDto.getAuthor());
                        ps.setLong(3, bookDto.getPageCount());
                        ps.setLong(4, bookDto.getUserId());
                        return ps;
                    }
                },
                keyHolder);

        bookDto.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {

        if (getBookById(bookDto.getId()) == null){
            return createBook(bookDto);
        }

        jdbcTemplate.update("UPDATE BOOK SET USER_ID=?, TITLE=?, AUTHOR=?, PAGE_COUNT=? WHERE ID=?",
                bookDto.getUserId(), bookDto.getTitle(), bookDto.getAuthor(), bookDto.getPageCount(), bookDto.getId());
        return bookDto;

    }

    @Override
    public BookDto getBookById(Long id) {
        // реализовать недстающие методы
        log.info("Book id to find: {}", id);
        Book foundBook = jdbcTemplate.query("SELECT * FROM BOOK WHERE ID = ?",
                        new BeanPropertyRowMapper<>(Book.class), new Object[]{id})
                .stream().findAny().orElse(null);

        log.info("Found book: {}", foundBook);
        return bookMapper.bookToBookDto(foundBook);
    }

    @Override
    public void deleteBookById(Long id) {
        // реализовать недстающие методы
        log.info("Book id to delete: {}", id);
        jdbcTemplate.update("DELETE FROM BOOK WHERE ID=?", id);
    }

    public List<Long> getBooksIdByPersonId(Long id){
        List<Book> books = jdbcTemplate.query("SELECT * FROM BOOK WHERE USER_ID = ?",
                new BeanPropertyRowMapper<>(Book.class), new Object[]{id});

        List<Long> ids = books
                .stream()
                .map(book -> book.getId())
                .toList();
        return ids;
    }
}
