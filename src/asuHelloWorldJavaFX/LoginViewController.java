package asuHelloWorldJavaFX;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class LoginViewController {
    private Scene scene;
    private Stage stage;
    private Login login = new Login();

    @FXML
    private TextField IDField;
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private void initialize() {
    	// initialize definitions ONCE
    	Definitions.initializeClassVariables();
    }

    public void submitLogin(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        // Get the user input from ID and Password fields
        String employeeID = IDField.getText();
        String password = passwordField.getText();

        // Encrypt the password
        String encryptedPassword = encryptPassword(password);

        // Store the credentials in a text file
        storeCredentials(employeeID, encryptedPassword);

        // Existing code to change scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainConsole.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Placeholder");
        MainConsoleViewController control = fxmlLoader.getController();
        //control.setLogin(login);
        stage.setScene(scene);
        stage.show();
    }

    private String encryptPassword(String password) throws NoSuchAlgorithmException {
        // Use SHA-256 to encrypt the password
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        // Return the Base64 encoded version of the encrypted password
        return Base64.getEncoder().encodeToString(encodedhash);
    }

    private void storeCredentials(String employeeID, String encryptedPassword) throws IOException {
        // Prepare the string to write to the file
        String userCredentials = employeeID + "," + encryptedPassword + "\n";

        // Write the encrypted credentials to the file, appending them so we don't overwrite existing data
        Files.write(Paths.get("credentials.txt"), userCredentials.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
