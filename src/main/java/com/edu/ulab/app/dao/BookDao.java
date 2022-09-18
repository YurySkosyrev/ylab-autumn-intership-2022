package com.edu.ulab.app.dao;

import com.edu.ulab.app.entity.Book;

public interface BookDao {
    Book createBook(Book book);

    Book updateBook(Book book);

    Book getBookById(Long id);

    void deleteBookById(Long id);
}
