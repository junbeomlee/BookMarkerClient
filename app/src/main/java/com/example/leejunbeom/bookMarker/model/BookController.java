package com.example.leejunbeom.bookMarker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 16. 3. 28..
 */
public class BookController {

    private List<Book> bookList=new ArrayList<Book>();

    public List<Book> getBookList(){
         return this.bookList;
    }

    public void addBook(Book book){
        this.bookList.add(book);
    }
}
