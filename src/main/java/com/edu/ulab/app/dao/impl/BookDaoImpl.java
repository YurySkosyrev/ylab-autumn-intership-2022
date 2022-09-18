package com.edu.ulab.app.dao.impl;

import com.edu.ulab.app.dao.BookDao;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.storage.Storage;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    private final Storage storage;

    public BookDaoImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Book createBook(Book book) {
        return storage.createBook(book);
    }

    @Override
    public Book updateBook(Book book) {
        if (book.getId() == null || storage.getBookById(book.getId()) == null) {
            return storage.createBook(book);
        } else {
            Book existingBook = storage.getBookById(book.getId());
            if (book.getAuthor() == null) {
                book.setAuthor(existingBook.getAuthor());
            }
            if (book.getTitle() == null) {
                book.setTitle(existingBook.getTitle());
            }
            if (book.getPageCount() == 0) {
                book.setPageCount(book.getPageCount());
            }
            if (book.getUserId() == null) {
                book.setUserId(existingBook.getUserId());
            }
        }
        return storage.updateBook(book);
    }

    @Override
    public Book getBookById(Long id) {
        Book book = storage.getBookById(id);
        if (book == null) {
            throw new NotFoundException("Book not found");
        }
        return book;
    }

    @Override
    public void deleteBookById(Long id) {
        storage.deleteBook(id);
    }
}
