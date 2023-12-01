package asuHelloWorldJavaFX;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogConsole extends Application {
	
    @Override
    public void start(Stage primaryStage) {
        // Create a TextArea to display the log content
        TextArea logTextArea = new TextArea();
        logTextArea.setEditable(false);

        // Create a ScrollPane to allow scrolling if the content is too large
        ScrollPane scrollPane = new ScrollPane(logTextArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Read the content of logs.txt and set it to the TextArea
        String logContent = readLogFile("logs.txt");
        logTextArea.setText(logContent);
        
        // Set up the primary stage
        primaryStage.setTitle("Log Viewer");
        primaryStage.setScene(new Scene(scrollPane, 400, 300));
        primaryStage.show();
    }

    private String readLogFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
