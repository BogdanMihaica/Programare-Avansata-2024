package org.example;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

class Export implements Command {
    protected String outputPath = "C:/Users/Bogdan/Desktop/Programare Avansata/Lab 5/export.json";
    protected Map<String, Object> data = new HashMap<>();


    public Map<String, Object> getData() {
        return data;
    }

    /**
     * The run() command from the Export command class uses an ObjectMapper from fasterxml.jackson.databind to write the corresponding json data to a specific outputPath ( I chose the parent folder of src
     */
    @Override
    public void run() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(outputPath), data);
            System.out.println("Exported data to JSON file: " + outputPath);
        } catch (IOException e) {
            System.out.println("Error exporting data: " + e.getMessage());
        }
    }
}