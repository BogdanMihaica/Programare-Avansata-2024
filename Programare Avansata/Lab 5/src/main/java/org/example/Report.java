package org.example;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import freemarker.template.Configuration;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
class Report implements Command {
    protected String templatePath;
    protected String outputPath="C:/Users/Bogdan/Desktop/Programare Avansata/Lab 5/report.html";
    protected Map<String, Object> dataModel = new HashMap<>();

    public Report(String templatePath) {
        this.templatePath = templatePath;
    }

    public Map<String, Object> getDataModel() {
        return dataModel;
    }

    /**
     * The run commmand from the Report command class uses the Configuration class given by FreeMarker to set the directory for the loading of the template then a Template object to get it, then populates the content of a data Map to that
     */
    @Override
    public void run() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        try {
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
            Template template = configuration.getTemplate("report_template.ftl");
            File outputFile = new File(outputPath);
            FileWriter fileWriter = new FileWriter(outputFile);
            template.process(dataModel, fileWriter);
            fileWriter.close();
            Desktop.getDesktop().open(outputFile);
        } catch (IOException | TemplateException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}