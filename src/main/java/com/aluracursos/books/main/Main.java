package com.aluracursos.books.main;

import com.aluracursos.books.models.APIResponse;
import com.aluracursos.books.models.Author;
import com.aluracursos.books.models.Book;
import com.aluracursos.books.services.APIConsumer;
import com.aluracursos.books.services.DataConversor;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final String URL = "https://gutendex.com/books/";
    private final APIConsumer consumer = new APIConsumer();
    private final DataConversor conversor = new DataConversor();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        getTopBooks();
        searchBook();
    }

    private void getTopBooks() {
        System.out.println("\n\uD83D\uDCDA Los 10 libros más descargados:");

        var json = consumer.getDataFromAPI(URL);
        var data = conversor.getData(json, APIResponse.class);

        data.books()
                .stream()
                .sorted(Comparator.comparing(Book::downloads).reversed())
                .limit(10)
                .map(book -> "\uD83D\uDCD6 " + book.title().toUpperCase())
                .forEach(System.out::println);
    }

    private void searchBook() {
        System.out.println("\n\uD83D\uDD0D Ingrese el título del libro que desea buscar:");

        var bookTitle = scanner.nextLine();
        var encodedTitle = URLEncoder.encode(bookTitle, Charset.defaultCharset());
        var json = consumer.getDataFromAPI(URL + "?search=" + encodedTitle);
        var data = conversor.getData(json, APIResponse.class);

        Optional<Book> searchedBook = data
                .books()
                .stream()
                // .peek(System.out::println)
                .filter(book -> book.title()
                        .toUpperCase()
                        .contains(bookTitle.toUpperCase()))
                .findFirst();

        if (searchedBook.isPresent()) {
            System.out.println("\uD83D\uDE3A Libro encontrado");
            Book book = searchedBook.get();

            Optional<String> author = book.authors()
                    .stream()
                    .map(Author::name)
                    .findFirst();

            System.out.println("Título: " + book.title());
            System.out.println("Autor: " + author.orElse(""));
            System.out.println("Descargas: " + book.downloads());
        } else {
            System.out.println("\uD83D\uDE3F Libro no encontrado");
        }
    }
}
