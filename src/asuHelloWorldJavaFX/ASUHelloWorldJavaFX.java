/* Author: Angela Zhang
 * 
 * Design: ASUHelloWorldJavaFX.java implements the Model (in the MVC model)
 * for the EffortLogger mainline/main console.
 */

package asuHelloWorldJavaFX;

import java.io.IOException;

//import application.DisplayViewController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class ASUHelloWorldJavaFX extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("MainConsole.fxml"));
			Scene scene = new Scene(root,600,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
    }
    
}