/*Author: Connor Onishi
 * 
 * Design: DefectConsoleViewController.java implements the Controller
 * for the Defect Console component of EffortLogger.
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

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DefectConsoleViewController extends Application {
	
	private DefectLog defect;
	private Date startDate;

	@FXML
	private ChoiceBox<String> projectSelect, LCStepInjected, defectCategory, LCStepRemoved;
	
	@FXML
	private TextArea comments, defectName, keywordsTextArea;
	
	@FXML
	private Button submitDefect;
	
	@FXML
	private Label label1, label2, label3, label4;
    
    @FXML
	private void initialize() {
    	
		// initialize definitions
		Definitions.initializeClassVariables();
		
		// set fixed menu options
		projectSelect.setItems(FXCollections.observableArrayList(Definitions.projectNames));
		
		LCStepInjected.setItems(FXCollections.observableArrayList(Definitions.LCstepNames));
		LCStepRemoved.setItems(FXCollections.observableArrayList(Definitions.LCstepNames));
		
		defectCategory.setItems(FXCollections.observableArrayList(DefectLog.DefectCategories));
		
		
		label2.setText("Defect Category");
		label3.setText("Lifecycle step injected");
		label4.setText("Lifecycle step removed");		
		
		startDate = new Date();
    }
		
		public void submitReport() {
			// create new Defect Log
			defect = new DefectLog();
			((DefectLog) defect).setProjectName(projectSelect.getValue());
			((DefectLog) defect).setDefectCategory(defectCategory.getValue());
			((DefectLog) defect).setLCstepInjected(LCStepInjected.getValue());
			((DefectLog) defect).setLCstepRemoved(LCStepRemoved.getValue());

			// Check required fields
		    if (projectSelect.getValue() == null) {
		        System.out.println("Please choose project name!");
		        return;
		       }
		    if ((defectCategory.getValue() == null)) {
		        System.out.println("Please choose defect!");
		        return;
		       }
			
		    if (LCStepInjected.getValue() == null) {
		        System.out.println("Please choose the life cycle step where the defect was injected!");
		        return;
		       }
			
		    if (LCStepRemoved.getValue() == null) {
		        System.out.println("Please choose the life cycle step where the defect was removed!");
		        return;
		       }

			// set common fields & save
		    defect.setKeywords(keywordsTextArea.getText());
		    defect.setUserStory(comments.getText());
			
			// save and print out new defect report
		    if (defect instanceof DefectLog) {
		    	((DefectLog) defect).save();
		      }
		    else {
		        System.out.println("Defect Log Uploaded!");
		        System.out.println();
		        defect.print();
		       }
		    
		    if (defect.getStartDate() == null) {
		    	defect.setStartDate(startDate);
		    }
		    defect.setEndDate();
			
			// reset log info
		    defect = null;
				
		    projectSelect.setValue(null);
		    defectCategory.setValue(null);
		    LCStepInjected.setValue(null);
		    LCStepRemoved.setValue(null);
		    
		    startDate = null;

		    keywordsTextArea.clear();
		    comments.clear();
					}
		
		//Prints out all Defect Reports
		public void viewReports() {
			defect.printAll();
		}
		
		@Override
		public void start(Stage arg0) throws Exception {
			// TODO Auto-generated method stub
			
		}}
		
