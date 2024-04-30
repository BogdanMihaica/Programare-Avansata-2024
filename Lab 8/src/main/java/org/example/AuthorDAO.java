package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO() throws SQLException {
        connection = DatabaseManager.getConnection();
    }

    public List<String> getAllAuthors() throws SQLException {
        List<String> authors = new ArrayList<>();
        String query = "SELECT * FROM authors"; // Retrieve all columns for each author

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Assuming 'author_id' and 'name' are columns in your authors table
                Author author=new Author(resultSet.getInt("author_id"),
                                         resultSet.getString("name")
                                         );



                // Construct a string representation of each author and add to the list
                String authorInfo = "Author ID: " + author.getid() + ", Name: " + author.getName();
                authors.add(authorInfo);

            }
        }

        return authors;
    }

    public void addAuthor(String name) throws SQLException {
        String queryCheck = "SELECT COUNT(*) FROM authors WHERE name = ?";
        String queryInsert = "INSERT INTO authors (name) VALUES (?)";

        try (PreparedStatement checkStatement = connection.prepareStatement(queryCheck);
             PreparedStatement insertStatement = connection.prepareStatement(queryInsert)) {

            // Check if the author already exists
            checkStatement.setString(1, name);
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                // Author doesn't exist, so insert the new author
                insertStatement.setString(1, name);
                insertStatement.executeUpdate();
                System.out.println("Author '" + name + "' added successfully.");
            } else {
               // System.out.println("Author '" + name + "' already exists.");
                // You can choose to throw an exception or handle it differently based on your requirement
            }
        }
    }
    public void deleteAuthor(String name) throws SQLException {
        String query = "DELETE FROM authors WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Author '" + name + "' not found in the database.");
            } else {
                System.out.println("Author '" + name + "' deleted successfully.");
            }
        }
    }

    public void addAuthorsFromCSV() throws IOException, CsvValidationException, SQLException {
        CSVReader reader=new CSVReader(new FileReader("C:\\Users\\Bogdan\\Desktop\\Homew\\src\\books.csv"));
        String[] nextLine;
        int i=0;
        while ((nextLine = reader.readNext()) != null) {
            //try to add author if he doesn't already exist
            if(i>0)//we skip the first line (it is occupied by column names)
            {
                addAuthor(nextLine[2]);
            }
            i++;
        }
    }
}
