package com.aluracursos.books.main;

import com.aluracursos.books.models.APIResponse;
import com.aluracursos.books.models.Book;
import com.aluracursos.books.services.APIConsumer;
import com.aluracursos.books.services.DataConversor;

import java.util.Comparator;

public class Main {
    private static final String URL = "https://gutendex.com/books/";
    private final APIConsumer consumer = new APIConsumer();
    private final DataConversor conversor = new DataConversor();

    public void showMenu() {
        var json = consumer.getDataFromAPI(URL);
        var data = conversor.getData(json, APIResponse.class);

        data.books()
                .stream()
                .sorted(Comparator.comparing(Book::downloads).reversed())
                .limit(10)
                .map(book -> book.title().toUpperCase())
                .forEach(System.out::println);
    }
}
