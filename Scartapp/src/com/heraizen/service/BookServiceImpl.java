package com.heraizen.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.heraizen.domain.Book;
import com.heraizen.util.MongoDBConnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class BookServiceImpl implements BookService{
   private List<Book> bookList=new ArrayList<Book>();
   private List<Book> addedCartBook=new ArrayList<Book>();
   private static BookServiceImpl bookServiceImpl=null;
    private  BookServiceImpl() {
		
	}
    public static BookServiceImpl getInstance() {
    	if(bookServiceImpl==null) {
    		synchronized (BookServiceImpl.class) {
				if(bookServiceImpl==null) {
					bookServiceImpl=new BookServiceImpl();
				}
			}
    	}
    	return bookServiceImpl;
    }
	@Override
	public List<Book> getAllBooks() {
		if(bookList.isEmpty()) {
			try (MongoClient client = MongoDBConnection.getMongoClient()) {
				MongoDatabase database = client.getDatabase("teamsinfo");
				MongoCollection<Book> collection = database.getCollection("bookdata", Book.class);
				FindIterable<Book> bookDetailsObj = collection.find();
				bookDetailsObj.forEach(book -> {
					bookList.add(book);
				});

			}
		}
		return bookList;
	}

	@Override
	public List<Book> getBookById(String bookId) {
		 bookList.forEach(book ->{	 
				 String id=""+book.getId();
				if( id.equalsIgnoreCase(bookId)) {
					try (MongoClient client = MongoDBConnection.getMongoClient()) {
						MongoDatabase database = client.getDatabase("teamsinfo");
						MongoCollection<Book> collection = database.getCollection("addedCart", Book.class);
					    collection.insertOne(book);
					    FindIterable<Book> addedBookDetails = collection.find();
					    addedBookDetails.forEach(ele-> {
					    	addedCartBook.add(ele);
					    });
					}
				}
			
		 });
		return addedCartBook;
	}

}
