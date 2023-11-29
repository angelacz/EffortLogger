/* Author: Angela Zhang
 * 
 * Design: MainConsoleViewController.java implements the Controller (in the MVC model)
 * for the EffortLogger mainline/main console.
 * 
 * Design goals: By using the MVC model, the application data (model), UI (view), and logic (control)
 * can be implemented separately. We also separate the different components/consoles of EffortLogger,
 * since each have distinct functionalities.
 * 
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.stage.Stage;

public class MainConsoleViewController {
	private Scene scene;
	private Stage stage;
	
	public void goToEffortConsole(ActionEvent event) {
	
  	  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortConsole.fxml"));
  	  
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  stage.setTitle("Effort Console");
  	  
  	  try {
  		  scene = new Scene(fxmlLoader.load(), 600, 600);
  		  stage.setScene(scene);
      	  stage.show();
  	  }
  	  catch (Exception e) {
  		  e.printStackTrace();
  	  } 
    }
	@FXML
    public void print() {
    	System.out.println("No Current Logs!");
    }
	public void goToEditConsole(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortConsoleEditor.fxml"));
        try {
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Effort Log Edit Console");
            scene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}