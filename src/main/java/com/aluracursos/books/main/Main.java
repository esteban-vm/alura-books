package com.aluracursos.books.main;

import com.aluracursos.books.models.Book;
import com.aluracursos.books.services.APIConsumer;
import com.aluracursos.books.services.DataConversor;

public class Main {
    private static final String URL = "https://gutendex.com/books/";
    private final APIConsumer consumer = new APIConsumer();
    private final DataConversor conversor = new DataConversor();

    public void showMenu() {
        var json = consumer.getDataFromAPI(URL);
        var books = conversor.getData(json, Book.class);
        System.out.println(books);
    }
}
