package asuHelloWorldJavaFX;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EffortLogEditorController {

    @FXML
    private ComboBox<String> projectSelect;
    @FXML
    private ComboBox<EffortLog> effortLogEntrySelect;
    @FXML
    private ComboBox<String> lifeCycleStepSelect;
    @FXML
    private ComboBox<String> effortCategorySelect;
    @FXML
    private ComboBox<?> subordinateSelect;

    @FXML
    private TextField dateField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField stopTimeField;
    @FXML
    private TextField otherDetailsField;
    private Scene scene;
    private Stage stage;

    @FXML
    public void proceedToEffortConsole(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EffortConsole.fxml"));
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
    
    @FXML
    public void initialize() {
        projectSelect.setItems(FXCollections.observableArrayList(Definitions.projectNames));

        projectSelect.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateEffortLogEntries(newSelection);
                updateLifecycleSteps(newSelection);
            }
        });

        effortLogEntrySelect.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateTextFieldsWithSelectedLog(newSelection);
            } else {
                clearTextFields();
            }
        });

        effortCategorySelect.setItems(FXCollections.observableArrayList(Definitions.effortCategoryNames));
    }

    private void updateTextFieldsWithSelectedLog(EffortLog selectedLog) {
        dateField.setText(formatDate(selectedLog.getStartDate()));
        startTimeField.setText(formatTime(selectedLog.getStartTime()));
        stopTimeField.setText(formatTime(selectedLog.getEndTime()));
        
        lifeCycleStepSelect.setValue(selectedLog.getLCStep());
        effortCategorySelect.setValue(selectedLog.getEffortCategory());
    }

    private void clearTextFields() {
        dateField.clear();
        startTimeField.clear();
        stopTimeField.clear();
        
        lifeCycleStepSelect.setValue(null);
        effortCategorySelect.setValue(null);
    }

    private void updateTextFieldsWithMostRecentLog(String projectName) {
        ArrayList<EffortLog> logs = Definitions.projectEffortLogs.get(projectName);
        if (logs != null && !logs.isEmpty()) {
            // Assuming the most recent log is the last one in the list
            EffortLog mostRecentLog = logs.get(logs.size() - 1);

            // Format and set date and times in the text fields
            dateField.setText(formatDate(mostRecentLog.getStartDate()));
            startTimeField.setText(formatTime(mostRecentLog.getStartTime()));
            stopTimeField.setText(formatTime(mostRecentLog.getEndTime()));
        } else {
            // Clear the fields if there are no logs for the selected project
            dateField.clear();
            startTimeField.clear();
            stopTimeField.clear();
        }
    }

    // Utility methods to format date and time (implement these according to your requirements)

private String formatDate(Date date) {
    if (date == null) {
        return "";
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
}

private String formatTime(long time) {
    // Assuming 'time' is in milliseconds
    Date date = new Date(time);
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    return timeFormat.format(date);
}


    public void updateEffortLogEntries(String projectName) {
        ArrayList<EffortLog> logs = Definitions.projectEffortLogs.get(projectName);
        effortLogEntrySelect.setItems(FXCollections.observableArrayList(logs != null ? logs : new ArrayList<>()));
    }

    private void updateLifecycleSteps(String projectName) {
        ArrayList<String> lcSteps = Definitions.projectToLCSteps.get(projectName);
        lifeCycleStepSelect.setItems(FXCollections.observableArrayList(lcSteps != null ? lcSteps : new ArrayList<>()));
    }
    
    @FXML 
    protected void updateEntry(ActionEvent event) {
    	String selectedProject = projectSelect.getValue();
        EffortLog selectedLog = effortLogEntrySelect.getValue();
        
        if (selectedProject != null && selectedLog != null) {
            // Get data from text fields
            String dateStr = dateField.getText();
            String startTimeStr = startTimeField.getText();
            String stopTimeStr = stopTimeField.getText();
            
            try {
                // Parse date and time strings into Date and long values
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                Date date = dateFormat.parse(dateStr);
                Date startTime = timeFormat.parse(startTimeStr);
                Date stopTime = timeFormat.parse(stopTimeStr);
                
                // Update the selected EffortLog with the new values
                selectedLog.setStartDate(date);
                selectedLog.setStartTime(startTime.getTime());
                selectedLog.setEndTime(stopTime.getTime());
                
                selectedLog.setLCStep(lifeCycleStepSelect.getValue());
                selectedLog.setEffortCategory(effortCategorySelect.getValue());
                
                // Update the EffortLog in your data structure (e.g., Definitions.projectEffortLogs)
                // You'll need to find the corresponding EffortLog in your data structure and update it
                
                // After updating, you may want to refresh the ComboBox
                updateEffortLogEntries(selectedProject);
            } catch (ParseException e) {
                showAlert("Invalid Date/Time Format", "Please enter dates in the format 'yyyy-MM-dd' and times in the format 'HH:mm'.");
            }
        } else {
            showAlert("Update Entry Error", "No project or effort log entry selected. Please select a project and an effort log entry to update.");
        }
    }

    @FXML
    protected void clearLog(ActionEvent event) {
        String selectedProject = projectSelect.getValue();
        if (selectedProject != null) {
            Definitions.projectEffortLogs.put(selectedProject, new ArrayList<>());
            updateEffortLogEntries(selectedProject); // Refresh the effort log entries ComboBox
            clearTextFields(); // Reset text fields
        }
    }

    @FXML
    protected void deleteEntry(ActionEvent event) {
        String selectedProject = projectSelect.getValue();
        EffortLog selectedLog = effortLogEntrySelect.getValue();
        if (selectedProject != null && selectedLog != null) {
            Definitions.projectEffortLogs.get(selectedProject).remove(selectedLog);
            updateEffortLogEntries(selectedProject); // Refresh the effort log entries ComboBox

            if (Definitions.projectEffortLogs.get(selectedProject).isEmpty()) {
                clearTextFields(); // Reset text fields if no more entries are left
            }
        }
    }

    @FXML
    protected void splitEntry(ActionEvent event) {
        EffortLog selectedLog = effortLogEntrySelect.getValue();
        String selectedProject = projectSelect.getValue();

        if (selectedProject != null && selectedLog != null) {
            // Calculate the midpoint of the time interval
            long startTime = selectedLog.getStartTime();
            long endTime = selectedLog.getEndTime();
            long midTime = startTime + (endTime - startTime) / 2;

            // Create a new log for the second half
            EffortLog secondHalf = new EffortLog(); // Properly duplicate the selectedLog attributes
            // ... set attributes for secondHalf based on selectedLog

            secondHalf.setStartTime(midTime);
            selectedLog.setEndTime(midTime);

            ArrayList<EffortLog> logs = Definitions.projectEffortLogs.get(selectedProject);
            if (logs != null) {
                logs.add(secondHalf);
            }

            updateEffortLogEntries(selectedProject); // Refresh the effort log entries ComboBox
        } else {
            showAlert("Split Entry Error", "No project or effort log entry selected. Please select a project and an effort log entry to split.");
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    // Other existing methods
}
