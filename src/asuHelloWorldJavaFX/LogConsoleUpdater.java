package asuHelloWorldJavaFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class LogConsoleUpdater extends Log {
    public static void main(String[] args) {
    	
    	Log log = new Log();
       /* String startTime = "";
        String endTime = "";
        String elapsedTime = "";
        String projectName = "";
        String LCstep = "";
        String effortCategory = "";
        String deliverable = "";
        String keywords = "";
        String userStory = "";  */
        String header = "==============================================================\n" + "NEW SET OF LOGS ARE LISTED BELOW\n" + "==============================================================\n\n";
     /*   String time = "";
        String log = "";
        String border = "";
        String displayNumLogs = "";
        String description = "";  */

        boolean running = true;
        int numLogs = 0;

        
        try {
            FileWriter myWriter = new FileWriter("logs.txt", true);
            myWriter.write(header);
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("error");
        } 
                                
                String border = "---------------------------------------------------------\n";
                String time = "Effort log with start time: " + log.startTime + ", end time: " + log.endTime + ", and elapsed time: " + log.elapsedTime + "\n";
                String newLog = "\t Project: " + log.projectName + "\n\t Life Cycle Step: ?????"  + "\n\t Effort Category: " + log.effortCategory + "\n\t Deliverable: ????" + "\n\t Keywords: " + log.keywords + "\n\t Description: ????"  + "\n";

                try {
                    FileWriter myWriter = new FileWriter("logs.txt", true);
                    myWriter.write(time);
                    myWriter.write(newLog);
                    myWriter.write(border);
                    myWriter.close();
                }
                catch (IOException e) {
                    System.out.println("error");
                }                
        
        
        String displayNumLogs = "\t\tThere were " + numLogs + " logs created this session\n";
        try {
            FileWriter myWriter = new FileWriter("logs.txt", true);
            myWriter.write(displayNumLogs);
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("error");
        }  


    }
}