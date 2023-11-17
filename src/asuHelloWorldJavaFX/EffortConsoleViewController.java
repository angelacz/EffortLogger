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

import asuHelloWorldJavaFX.Definitions;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;

public class EffortConsoleViewController {
	private EffortLog log = new EffortLog();
	
	private BooleanProperty isClockStarted = new SimpleBooleanProperty(false);
	
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
		// initialize definitions
		Definitions.initializeClassVariables();
		
		// set menu options for log attributes
		projectChoiceBox.setItems(FXCollections.observableArrayList(Definitions.projectNames));
		LCstepChoiceBox.setItems(FXCollections.observableArrayList(Definitions.LCstepNames));
		effortCategoryChoiceBox.setItems(FXCollections.observableArrayList(Definitions.effortCategoryNames));
		deliverableChoiceBox.setItems(FXCollections.observableArrayList(Definitions.effortCategoryChoiceNames));
		
		// TODO: set listeners or binding (to menu, based on selected options in other menus) for menu options
		
		isClockStarted.addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				clockLabel.setText("Clock is started");
				clockLabel.setTextFill(Color.GREEN);
			}
			else {
				clockLabel.setText("Clock is stopped");
				clockLabel.setTextFill(Color.RED);
			}
		});
	}
	
	public void startTask() {
		isClockStarted.setValue(true);
		log.setStartDate();
	}
	
	public void endTask() {
		// check for incorrect/incomplete input
		if (!isClockStarted.getValue()) {
			System.out.println("Clock has not started yet!");
			return;
		}
		else if (projectChoiceBox.getValue() == null) {
			System.out.println("Please choose project name!");
			return;
		}
		else if (LCstepChoiceBox.getValue() == null) {
			System.out.println("Please choose LC step!");
			return;
		}
		else if (effortCategoryChoiceBox.getValue() == null) {
			System.out.println("Please choose effort category!");
			return;
		}
		else if (deliverableChoiceBox.getValue() == null) {
			System.out.println("Please choose deliverable!");
			return;
		}
		
		// set log attributes
		log.setProjectName((String) projectChoiceBox.getValue());
		log.setLCStep((String) LCstepChoiceBox.getValue());
		log.setEffortCategory((String) effortCategoryChoiceBox.getValue());
		log.setDeliverable((String) deliverableChoiceBox.getValue());
		
		log.setKeywords(keywordsTextArea.getText());
		log.setUserStory(userStoryTextArea.getText());
		
		log.setEndDate();
		log.setElapsedTime();
		
		// save current log
		log.save();
		
		// reset log info
		log = new EffortLog();
		isClockStarted.setValue(false);
		
		projectChoiceBox.setValue(null);
		LCstepChoiceBox.setValue(null);
		effortCategoryChoiceBox.setValue(null);
		deliverableChoiceBox.setValue(null);
		
		keywordsTextArea.clear();
		userStoryTextArea.clear();
		
	}
}
