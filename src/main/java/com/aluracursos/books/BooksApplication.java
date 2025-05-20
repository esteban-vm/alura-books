package com.aluracursos.books;

import com.aluracursos.books.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(BooksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var main = new Main();
        main.showMenu();
    }
}
