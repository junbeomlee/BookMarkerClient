package com.example.leejunbeom.test.model;

import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jun on 16. 3. 28..
 */
public class BookControllerTest {

    private BookController bookController;
    @Before
    public void setUp(){
        this.bookController=new BookController();
    }
    @After
    public void tearDown(){

    }
    @Test
    public void should_add_book_work(){
        Book book = new Book();
        book.setSymbolicRequest("801이준범");
        bookController.addBook(book);
        assertEquals("fald book add in the list",1,bookController.getBookList().size());
        assertEquals("fail book add ", "801이준범", bookController.getBookList().get(0).getSymbolicRequest());
    }
}
