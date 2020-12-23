package com.heraizen.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.heraizen.domain.Book;

public interface BookService {
  public List<Book> getAllBooks();
  public List<Book> getBookById(String bookId);
}
