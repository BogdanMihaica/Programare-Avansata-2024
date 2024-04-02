package org.example;
import net.bytebuddy.implementation.bytecode.Throw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class SimpleShell {
    /**
     * For the shell I started an infinite loop that can only be terminated using the "exit" command in the shell or by brute force. It takes an array of strings as parameters and launches the processCommand function for the given input
     */
    public void start(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.print("Enter a command: ");
                String input = reader.readLine();

                String[] commandTokens = input.split(" ");
                String command = commandTokens[0];
                String[] arguments = new String[commandTokens.length - 1];
                System.arraycopy(commandTokens, 1, arguments, 0, arguments.length);

                processCommand(command, arguments);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The processCommand function uses a switch to check the input given from the start function and handles the command and also throwing exceptions for invalid sets of data
     */
    private static void processCommand(String command, String[] arguments) {
        String tempPath="C:/Users/Bogdan/Desktop/Programare Avansata/Lab 5";
        switch (command) {
            case "echo" -> echo(arguments);
            case "view" -> {
                try
                {
                    if(arguments.length>1)
                    {
                        throw new IOException("Too many arguments for the view command");
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught: " + e.getMessage());
                }
                String path = arguments[0];
                Command view = new View(path);
                view.run();
            }
            case "report" -> {
                try
                {
                    if(arguments.length>0)
                    {
                        throw new IOException("Too many arguments for the report command");
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught: " + e.getMessage());
                }

                Report report = new Report(tempPath);
                StringBuilder content = new StringBuilder();
                var data=RepositoryManager.getDocuments();
                for(Person key : data.keySet())
                {
                    for(Document D : data.get(key)) {
                        String docName = "(" + key.id() + ")" + key.name() + "-" + D.name() + "." + D.extension();
                        String docs =key.name() + ":  <a href=\"" +Main.repoPath+"/"+docName+"\">"+docName+ "</a><br>";
                        content.append(docs);
                    }
                }
                report.getDataModel().put("title", "Report : Content of repository \"Employees\"");
                report.getDataModel().put("date", LocalDate.now());
                report.getDataModel().put("content", content);
                report.run();
            }
            case "export" -> {
                try
                {
                    if(arguments.length>0)
                    {
                        throw new IOException("Too many arguments for the export command");
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught: " + e.getMessage());
                }
                Export export = new Export();
                export.getData().put("data", RepositoryManager.documents);
                export.run();
            }
            case "exit" -> {
                System.out.println("Exiting the shell.");
                System.exit(0);
            }
            default -> System.out.println("Unknown command: " + command);
        }
    }

    private static void echo(String[] arguments) {

        for (String arg : arguments) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }
}