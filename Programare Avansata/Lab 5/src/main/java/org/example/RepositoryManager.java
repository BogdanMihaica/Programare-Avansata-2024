
package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RepositoryManager {
    protected String directory;
    public static Map<Person, List<Document>> documents = new HashMap<>();

    public RepositoryManager(String directory) {
        this.directory = directory;
    }

    /**
     * To generate the repository I used a Path object constructed with a String that represents the path name and I threw an exception for the createDirectories function
     */
    public void generateRepository() {
        String folderPath = directory;
        Path folder = Paths.get(folderPath);
        try {
            Files.createDirectories(folder);

        } catch (IOException e) {
            System.err.println("Failed to create the folder: " + e.getMessage());
        }
    }

    /**
     * the addToPerson method adds a document to a list from a key of type Person in a HashMap
     * @param A
     * @param B
     */
    public void addToPerson(Person A, Document B) {
        List<Document> existing = new LinkedList<>();

        if (!documents.containsKey(A)) {
            existing.add(B);
            documents.put(A, existing);
        } else {
            existing = documents.get(A);
            if (existing.contains(B)) {
                System.err.println("Document with this name already exist for this person");
            } else {
                existing.add(B);
                documents.put(A, existing);
            }
        }

    }

    public static Map<Person, List<Document>> getDocuments() {
        return documents;
    }

    /**
     * The addDocToRepo function adds a document using the person's id, name and the name of the project. If the file already exists in the folder or the folder does not exist, we throw an exception
     *
     */
    public void addDocToRepo(Person person, Document document) {
        String fileName = "(" + person.id() + ")" + person.name() + "-" + document.name() + "." + document.extension();
        File folder = new File(directory);
        File file = new File(fileName);
        try {
            File[] filesInFolder = folder.listFiles();
            boolean fileExists = false;
            if (filesInFolder != null) {
                for (File f : filesInFolder) {
                    if (f.getName().equals(file.getName())) {
                        fileExists = true;
                        break;
                    }
                }
            }
            if (!fileExists) {
                if (file.createNewFile()) {
                    System.out.println("File created successfully.");
                } else {
                    System.out.println("Failed to create the file.");
                }
            } else {
                System.out.println("File already exists in the folder.");
            }
        } catch (IOException e) {
            System.err.println("Failed to create the file: " + e.getMessage());
        }

        File newFile = new File(folder, file.getName());
        if (file.renameTo(newFile)) {
            System.out.println("File moved successfully.");
        }
    }

    public void loadDocuments() {
        File folder = new File(directory);
        if (folder.exists() && folder.isDirectory()) {
            String[] files = folder.list();
            if (files != null) {
                System.out.println("Files in the folder:");
                for (String fileName : files) {
                    System.out.println(fileName);
                }
            } else {
                System.out.println("No files in the folder.");
            }
        } else {
            System.out.println("Folder not found or is not a directory.");
        }
    }

}
