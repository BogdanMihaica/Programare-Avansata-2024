package org.example;


public class Main {
    public static String repoPath= "C:/Users/Bogdan/Desktop/Programare Avansata/Lab 5/Employees";
    public static void main(String[] args) {
        SimpleShell shell = new SimpleShell();

        RepositoryManager manager = new RepositoryManager(repoPath);
        manager.generateRepository();
        Document doc1  = new Document("CV", "jpg");
        Document doc2 = new Document("resume", "pdf");
        Document doc3 = new Document("rezumat", "txt");
        Person person1 = new Person(12, "Bogdan");
        Person person2 = new Person(13, "Alex");
        manager.addToPerson(person1, doc1);
        manager.addToPerson(person1, doc3);
        manager.addDocToRepo(person1, doc3);
        manager.addDocToRepo(person1, doc1);
        manager.addToPerson(person2, doc2);
        manager.addDocToRepo(person2, doc2);
        manager.loadDocuments();
        shell.start();
    }
    public String getPath()
    {
        return repoPath;
    }
}