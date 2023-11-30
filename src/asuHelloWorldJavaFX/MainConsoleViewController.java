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

package asuHelloWorldJavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainConsoleViewController {
	private Scene scene;
	private Stage stage;
	
	// Effort Console
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
	
	// Defect Console
	public void goToDefectConsole(ActionEvent event) {
		
  	  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefectConsole.fxml"));
  	  
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  stage.setTitle("Defect Console");
  	  
  	  try {
  		  scene = new Scene(fxmlLoader.load(), 600, 600);
  		  stage.setScene(scene);
      	  stage.show();
  	  }
  	  catch (Exception e) {
  		  e.printStackTrace();
  	  } 
    }
	
	// Editor Console
	public void goToEditorConsole(ActionEvent event) {
		
  	  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortConsoleEditor.fxml"));
  	  
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  stage.setTitle("Effort Log Editor");
  	  
  	  try {
  		  scene = new Scene(fxmlLoader.load(), 600, 600);
  		  stage.setScene(scene);
      	  stage.show();
  	  }
  	  catch (Exception e) {
  		  e.printStackTrace();
  	  } 
  	}
	

}
