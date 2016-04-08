package com.example.leejunbeom.bookMarker.model;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 16. 3. 28..
 */
public class BookController {

    private ArrayList<Book> bookList=new ArrayList<Book>();

    public ArrayList<Book> getBookList(){
         return this.bookList;
    }

    public void addBook(Book book){
        this.bookList.add(book);
    }
}
