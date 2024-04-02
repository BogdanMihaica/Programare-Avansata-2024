package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class View implements Command{
    protected String filePath;

    public View(String filePath) {
        this.filePath = "C:/Users/Bogdan/Desktop/Programare Avansata/Lab 5/Employees/"+filePath;
    }

    /**
     * The run function from the View command class  checks if the file you want to view exists and uses the Desktop class to open it
     */
    @Override
    public void run() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("File not found: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
}
