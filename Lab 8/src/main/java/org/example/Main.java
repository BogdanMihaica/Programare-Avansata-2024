package org.example;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            AuthorDAO authorDAO = new AuthorDAO();
            BookDAO bookDAO = new BookDAO();


            boolean addedAlready=true; //changed manually

            authorDAO.addAuthor("Mihai Eminescu");
            authorDAO.addAuthor("Ion Creanga");
            authorDAO.addAuthor("Frantz Schubert");
            if(!addedAlready)
            {
                authorDAO.addAuthorsFromCSV();
                bookDAO.addBooksFromCSV();
            }
            bookDAO.addBook("Amintiri din copilarie","ro", "10/09/1860", 200, 20);
            List<String> authors = authorDAO.getAllAuthors();
            System.out.println("Authors:");
            for (String author : authors) {
                System.out.println(author);
            }
            System.out.println();
            List<String> books = bookDAO.getAllBooks();
            System.out.println("Books:");
            for (String book : books) {
                System.out.println(book);
            }

            // Close connection
            DatabaseManager.closeDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
