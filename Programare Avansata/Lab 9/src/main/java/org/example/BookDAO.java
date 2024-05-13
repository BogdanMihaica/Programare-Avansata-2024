package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO() throws SQLException {
        connection = DatabaseManager.getConnection();
    }
    public List<String> getAllBooks() throws SQLException {
        List<String> books = new ArrayList<>();
        String query = "SELECT * FROM books"; // Retrieve all columns for each author

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {

                Book book = new Book(resultSet.getInt("book_id"),
                        resultSet.getString("title"),
                        resultSet.getString("language"),
                        resultSet.getString("publication_date"),
                        resultSet.getInt("pages"),
                        resultSet.getInt("author_id")
                        );



                String bookInfo =
                        "Book ID: " + book.getId() +
                        ", Title: " + book.getTitle()+
                        ", Language: "+book.getLanguage() +
                        ", Publication Date: "+book.getPublication_date()+
                        ", Pages: "+ book.getPages()+
                        ", Author ID: "+book.getAuthor_id();
                books.add(bookInfo);

            }
        }

        return books;
    }
    private int findAuthorIdByName(String authorName) throws SQLException {
        String query = "SELECT author_id FROM authors WHERE name = ?";
        int authorId = -1;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, authorName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                authorId = resultSet.getInt("author_id");
            } else {
                System.out.println("Author '" + authorName + "' not found in database.");

            }
        }

        return authorId;
    }
    public void addBook(String title,String language, String publication_date,int pages, int author_id) throws SQLException {
        String query = "INSERT INTO books (title,language,publication_date,pages,author_id) VALUES (?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setString(2,language);
            statement.setString(3,publication_date);
            statement.setInt(4,pages);
            statement.setInt(5,author_id);
            statement.executeUpdate();
        }
        System.out.println("Book "+title+" added succesfully");
    }
    public void deleteBook(String name) throws SQLException {
        String query = "DELETE FROM books WHERE title = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Book '" + name + "' not found in the database.");
            } else {
                System.out.println("Book '" + name + "' deleted successfully.");
            }
        }
    }

    public static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false; // Empty or null string cannot be parsed
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i == 0 && ch == '-') {
                if (str.length() == 1) {
                    return false; // Just a '-' sign with no digits
                }
                continue; // Skip the '-' sign at the beginning
            }
            if (!Character.isDigit(ch)) {
                return false; // Non-digit character found
            }
        }

        return true; // All characters are digits (or '-' at the beginning for negative numbers)
    }
    public void addBooksFromCSV() throws IOException, CsvValidationException, SQLException {
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Bogdan\\Desktop\\Homew\\src\\books.csv"));
        String [] nextLine;
        System.out.println("Books Fetched From CSV: ");
        int i=0;
        while ((nextLine = reader.readNext()) != null) {
           if(i>0)
           {
                    if(isInteger(nextLine[7]))
                    {
                        int id = findAuthorIdByName(nextLine[2]);
                        if (id != -1) {
                            addBook(nextLine[1], nextLine[6], nextLine[10], (int) Integer.parseInt(nextLine[7]), id);
                        }
                    }
           }

           i++;
        }
    }
}
