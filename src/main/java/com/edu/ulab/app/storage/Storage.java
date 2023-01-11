//package com.edu.ulab.app.storage;
//
//import com.edu.ulab.app.entity.Book;
//import com.edu.ulab.app.entity.User;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class Storage {
//
//    private final Map<Long, User> userTable;
//    private final Map<Long, Book> bookTable;
//    private Long userId;
//    private Long bookId;
//
//    public Storage() {
//        userTable = new HashMap<>();
//        bookTable = new HashMap<>();
//        userId = 1L;
//        bookId = 1L;
//    }
//
//    public User createUser(User user) {
//        user.setId(userId);
//        user.setBooks(new ArrayList<>());
//        userTable.put(userId, user);
//        userId++;
//
//        return user;
//    }
//
//    public User updateUser(User user) {
//        userTable.remove(user.getId());
//        userTable.put(user.getId(), user);
//
//        return user;
//    }
//
//    public User getUserById(Long id) {
//        return userTable.getOrDefault(id, null);
//    }
//
//
//    public void deleteUser(Long id) {
//        userTable.remove(id);
//    }
//
//    public Book createBook(Book book) {
//        book.setId(bookId);
//        if (userTable.containsKey(book.getUserId())) {
//            userTable.get(book.getUserId()).getBooks().add(bookId);
//        }
//        bookTable.put(bookId, book);
//        bookId++;
//
//        return book;
//    }
//
//    public Book updateBook(Book book) {
//        bookTable.remove(book.getId());
//        bookTable.put(book.getId(), book);
//        List<Long> books = userTable.get(book.getUserId()).getBooks();
//        if (!books.contains(book.getId())) {
//            books.add(book.getId());
//            userTable.get(book.getUserId()).setBooks(books);
//        }
//
//        return book;
//    }
//
//    public Book getBookById(Long id) {
//        return bookTable.getOrDefault(id, null);
//    }
//
//    public void deleteBook(Long id) {
//        bookTable.remove(id);
//    }
//}

//todo создать хранилище в котором будут содержаться данные
// сделать абстракции через которые можно будет производить операции с хранилищем
// продумать логику поиска и сохранения
// продумать возможные ошибки
// учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
// продумать что у узера может быть много книг и нужно создать эту связь
// так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции

