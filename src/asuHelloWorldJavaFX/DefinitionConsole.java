package asuHelloWorldJavaFX;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DefinitionConsole extends Application {

	Definitions def = new Definitions();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Definitions Display");

        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        // Run your method and set the text in the TextArea
        runDisplayDefinitions(textArea);

        VBox vBox = new VBox(textArea);
        Scene scene = new Scene(vBox, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void runDisplayDefinitions(TextArea textArea) {
        // Simulating your displayDefinitions method
        StringBuilder definitionsText = new StringBuilder();
        definitionsText.append("\n==============================================================\n")
                .append("DEFINITIONS ARE LISTED BELOW\n")
                .append("==============================================================\n");

        // Add project names
        definitionsText.append("\nProject Names:\n");
        for (String projectName : def.projectNames) {
            definitionsText.append("\t- ").append(projectName).append("\n");
        }

        // Add lifecycle steps for each project
        definitionsText.append("\nLifecycle Steps:\n");
        for (String projectName : def.projectNames) {
            definitionsText.append("\t- ").append(projectName).append(": ").append(def.projectToLCSteps.get(projectName)).append("\n");
        }

        // Add effort categories and their choices
        definitionsText.append("\nEffort Categories and Choices:\n");
        for (String effortCategory : def.effortCategoryNames) {
            definitionsText.append("\t- ").append(effortCategory).append(": ").append(def.effortCategoryToChoices.get(effortCategory)).append("\n");
        }

        // Update the text in the TextArea on the JavaFX application thread
        Platform.runLater(() -> textArea.setText(definitionsText.toString()));
    }
}
