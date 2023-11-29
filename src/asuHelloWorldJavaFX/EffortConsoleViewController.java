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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class EffortConsoleViewController {
	private Scene scene;
	private Stage stage;
	
	private Log log;
	private Date startDate;
	
	@FXML
	private ChoiceBox<String> logType;
	
	private BooleanProperty isClockStarted = new SimpleBooleanProperty(false);
	
	@FXML
	private Label clockLabel;
	@FXML
	private Button startStopButton;
	
	@FXML
	private ChoiceBox<String> choiceBox1, choiceBox2, choiceBox3, choiceBox4;
	@FXML
	private Label label1, label2, label3, label4;
	
	@FXML
	private TextArea keywordsTextArea, userStoryTextArea;
	
	private ObservableList<String> emptyChoices = FXCollections.observableArrayList();
	private ObservableList<String> logTypeChoices = FXCollections.observableArrayList("Effort Log", "Defect Log");
	
	@FXML
	private void initialize() {
		// set fixed menu options for: log types, project names (box 1)
		logType.setItems(logTypeChoices);
		choiceBox1.setItems(FXCollections.observableArrayList(Definitions.projectNames));
		
		// set dynamic menu options (boxes 2-4) with listeners
		
		// listener for log type choice box
		logType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
				// set labels 2-4
				if ((Integer) newVal == 0) {
					label2.setText("Lifecycle step");
					label3.setText("Effort Catgeory");
					label4.setText("Deliverable");					
				}
				else {
					label2.setText("Defect");
					label3.setText("Lifecycle step injected");
					label4.setText("Lifecycle step removed");
				}
				
				// reset choice boxes 2-4
				choiceBox2.setItems(emptyChoices);
				choiceBox3.setItems(emptyChoices);
				choiceBox4.setItems(emptyChoices);
			}
		});
		
		// listener for project name (box 1)
		choiceBox1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
				Integer newInt = (Integer) newVal;
			
				// No selection for project name (box 1)
				if (newInt == -1) {
					choiceBox2.setItems(emptyChoices);
				}

				else {
					String projectName = choiceBox1.getItems().get((int) newInt);
					
					// Effort Log
					if (logType.getValue() == logTypeChoices.get(0)) {
						// set choices for LCstep (box 2)
						choiceBox2.setItems(FXCollections.observableArrayList(Definitions.projectToLCSteps.get(projectName)));
					}
					
					// Defect Log
					else {
						// set choices for defect logs (box 2)
						List<DefectLog> defectLogs = Definitions.projectDefectLogs.get(projectName);
						System.out.println("Num defect logs for project " + projectName + ": "+ defectLogs.size());
						List<String> defectLogNames = defectLogs.stream().map(l -> l.toString()).collect(Collectors.toList());
						choiceBox2.setItems(FXCollections.observableArrayList(defectLogNames));
					}
				}
				
				// reset remaining choice boxes
				choiceBox3.setValue(null);
				choiceBox4.setValue(null);
				choiceBox3.setItems(emptyChoices);
				choiceBox4.setItems(emptyChoices);
			}
		});
		
		// listener for choice box 2 (effort log: lifecycle step, defect log: defect)
		choiceBox2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldVal, Number newVal) {
				Integer newInt = (Integer) newVal;
				
				// No selection for choice box 2
				if (newInt == -1) {
					choiceBox3.setItems(emptyChoices);
					choiceBox4.setItems(emptyChoices);
				}
				
				else {
					String projectName = choiceBox1.getValue();
					String LCstep = choiceBox2.getItems().get((int) newInt);
					
					// Effort Log
					if (logType.getValue() == logTypeChoices.get(0)) {
					
						// set default effort category type (box 3) and choice (box 4)
						List<String> defaultEffortCategory = Definitions.LCstepToDefaultEffortCategory.get(LCstep);
						String effortCategory = defaultEffortCategory.get(0), effortCategoryChoice = defaultEffortCategory.get(1);
						
						choiceBox3.setValue(effortCategory);
						choiceBox4.setValue(effortCategoryChoice);
					}
					
					// Defect Log
					else {
						
						// set LC step injected (box 3) & removed (box 4)
						List<String> projectLCstepNames = Definitions.projectToLCSteps.get(projectName);
						choiceBox3.setItems(FXCollections.observableArrayList(projectLCstepNames));
						choiceBox4.setItems(FXCollections.observableArrayList(projectLCstepNames));
						
						// set existing defect log attributes
						DefectLog defectLog = Definitions.projectDefectLogs.get(projectName).get((int) newVal);
						
						choiceBox3.setValue(defectLog.getLCstepInjected());
						choiceBox4.setValue(defectLog.getLCstepRemoved());
						keywordsTextArea.setText(String.join(", ", defectLog.getKeywords()));
						userStoryTextArea.setText(defectLog.getUserStory());
					}
				}
			}
		});
			
		// listener for timer
		isClockStarted.addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				clockLabel.setText("Clock is started");
				clockLabel.setTextFill(Color.GREEN);
				startStopButton.setText("Stop activity");
			}
			else {
				clockLabel.setText("Clock is stopped");
				clockLabel.setTextFill(Color.RED);
				startStopButton.setText("Start activity");
			}
		});
	}
	
	public void toggleClock() {
		if (!isClockStarted.getValue()) {
			startTask();
		}
		else {
			endTask();
		}
	}
	
	public void startTask() {
		isClockStarted.setValue(true);
		startDate = new Date();
	}
	
	public void endTask() {
		// Check required fields
		if (logType.getValue() == null) {
			System.out.println("Please choose log type!");
			return;
		}
		if (choiceBox1.getValue() == null) {
			System.out.println("Please choose project name!");
			return;
		}
		if ((logType.getValue() == logTypeChoices.get(1)) && (choiceBox2.getValue() == null)) {
			System.out.println("Please choose defect!");
			return;
		}
		
		// Effort Log
		if (logType.getValue() == logTypeChoices.get(0)) {
			log = new EffortLog();
			
			// set fields
			((EffortLog) log).setProjectName(choiceBox1.getValue());
			((EffortLog) log).setLCStep(choiceBox2.getValue());
			((EffortLog) log).setEffortCategory(choiceBox3.getValue());
			((EffortLog) log).setDeliverable(choiceBox4.getValue());
		}
		
		// Defect Log
		else {
			log = Definitions.projectDefectLogs.get(choiceBox1.getValue()).get(choiceBox2.getSelectionModel().getSelectedIndex());
			
			// set fields
			((DefectLog) log).setLCstepInjected(choiceBox3.getValue());
			((DefectLog) log).setLCstepRemoved(choiceBox4.getValue());
		}

		// set common fields & save
		
		log.setKeywords(keywordsTextArea.getText());
		log.setUserStory(userStoryTextArea.getText());
		
		if (log.getStartDate() == null) {
			log.setStartDate(startDate);
		}
		log.setEndDate();
		log.setElapsedTime();
		
		// save new effort logs
		if (log instanceof EffortLog) {
			((EffortLog) log).save();
		}
		else {
			System.out.println("Timed Defect Log!");
			System.out.println();
			log.print();
		}
		
		// reset log info
		log = null;
		isClockStarted.setValue(false);
		startDate = null;
		
		choiceBox1.setValue(null);
		choiceBox2.setValue(null);
		choiceBox3.setValue(null);
		choiceBox4.setValue(null);

		keywordsTextArea.clear();
		userStoryTextArea.clear();
		
	}
	
	public void goToMainConsole(ActionEvent event) {
		
  	  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainConsole.fxml"));
  	  
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  stage.setTitle("Main Console");
  	  
  	  try {
  		  scene = new Scene(fxmlLoader.load(), 600, 400);
  		  stage.setScene(scene);
      	  stage.show();
  	  }
  	  catch (Exception e) {
  		  e.printStackTrace();
  	  } 
    }
}
