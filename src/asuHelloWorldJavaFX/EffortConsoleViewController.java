/* Author: Angela Zhang
 * 
 * Design: EffortConsoleViewController.java implements the Controller (in the MVC model)
 * for the Effort Console component of EffortLogger.
 * 
 * Design goals: By using the MVC model, the application data (model), UI (view), and logic (control)
 * can be implemented separately. We also separate the different components/consoles of EffortLogger,
 * since each have distinct functionalities.
 * 
 */


package asuHelloWorldJavaFX;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EffortConsoleViewController {
	private EffortLog log = new EffortLog();
	
	private boolean isClockStarted = false;
	
	String[] projectList = {"Project 1", "Project 2"};
	String[] LCstepList = {"Lifecycle step 1", "Lifecycle step 2"};
	String[] effortCategoryList = {"Effort Category: Tasks", "Effort Category: Defects"};
	String[] deliverableList = {"Task Deliverable", "Defect Category"};
	
	@FXML
	private Label clockLabel;
	
	@FXML
	private ChoiceBox projectChoiceBox;
	
	@FXML
	private ChoiceBox LCstepChoiceBox;
	
	@FXML
	private ChoiceBox effortCategoryChoiceBox;
	
	@FXML
	private ChoiceBox deliverableChoiceBox;
	
	@FXML
	private TextArea keywordsTextArea;
	
	@FXML
	private TextArea userStoryTextArea;
	
	
	@FXML
	private void initialize() {
		// set menu options for log attributes
		projectChoiceBox.setItems(FXCollections.observableArrayList(projectList));
		LCstepChoiceBox.setItems(FXCollections.observableArrayList(LCstepList));
		effortCategoryChoiceBox.setItems(FXCollections.observableArrayList(effortCategoryList));
		deliverableChoiceBox.setItems(FXCollections.observableArrayList(deliverableList));
		
		// set listeners for log attributes
		projectChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
		         (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
		        	log.setProjectName(projectList[new_val.intValue()]);
		            });
		
		LCstepChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
		         (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
		        	 log.setLCStep(LCstepList[new_val.intValue()]);
		            });
		
		effortCategoryChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
		         (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
		        	 log.setEffortCategory(effortCategoryList[new_val.intValue()]);
		            });
		
		deliverableChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
		         (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
		        	 log.setDeliverable(deliverableList[new_val.intValue()]);
		            });
		
		// create bindings for labels
//		clockLabel.textProperty().bind(Bindings.createStringBinding(() -> {
//		    String s = " ";
//		    if (isClockStarted)
//		        s = "Clock is started";
//		     else
//		        s = "Clock is stopped";
//
//		    return s;
//		}));
		
	}
	
	public void startTask() {
		isClockStarted = true;
		log.setStartTime(System.currentTimeMillis());
	}
	
	public void endTask() {
		// check for incorrect/incomplete input
		if (!isClockStarted) {
			System.out.println("Clock has not started yet!");
			return;
		}
		else if (log.getProjectName() == null) {
			System.out.println("Please choose project name!");
			return;
		}
		else if (log.getLCStep() == null) {
			System.out.println("Please choose LC step!");
			return;
		}
		else if (log.getEffortCategory() == null) {
			System.out.println("Please choose effort category!");
			return;
		}
		else if (log.getDeliverable() == null) {
			System.out.println("Please choose deliverable!");
			return;
		}
		
		// input correct
	
		// save current log
		log.setEndTime(System.currentTimeMillis());
		log.setElapsedTime(log.getEndTime() - log.getStartTime());
		
		log.setKeywords(keywordsTextArea.getText());
		log.setUserStory(userStoryTextArea.getText());

		log.save();
		
		// reset log info
		log = new EffortLog();
		isClockStarted = false;
		
		keywordsTextArea.clear();
		userStoryTextArea.clear();
		
		
	}
	
	
}
