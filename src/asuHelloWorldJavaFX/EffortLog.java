/* Author: Angela Zhang
 * 
 * Design: EffortLog.java implements the class representing effort and defect logs
 * 
 * Design goals: The EffortLogger system interacts with effort and defect logs 
 * in a way that can be represented by an entity class. Logs are associated with
 * a number of attributes (such as project and elapsed time), and allow a number of 
 * operations on those attributes.
 * 
 */

package asuHelloWorldJavaFX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.concurrent.TimeUnit;

public class EffortLog extends Log {
	
	// effort log attributes
	String LCstep;
	String deliverable;
	
	public EffortLog() {
		super();
	}
	
	// save functions
	
	public void save() {
		Definitions.projectEffortLogs.get(this.projectName).add(this); 
		
		// print all effort logs
		System.out.println("Saved Effort Log!");
		System.out.println();
		print();
	}
	
	// print functions
	
	public void print() {
		System.out.println("---------------------------------------------------");
		System.out.println("Effort Log");
		System.out.println("Start date: " + startDate);
		System.out.println("End date: " + endDate);
		System.out.println("Elapsed time: " + this.getElapsedTimeAsTimeUnit() + " " + Log.timeUnit);
		
		System.out.println("\t Project: " + projectName);
		System.out.println("\t Lifecycle step: " + LCstep);
		System.out.println("\t Effort category: " + effortCategory);
		System.out.println("\t Deliverable: " + deliverable);
		System.out.println();
		
		System.out.println("\t Keywords: ");
		for (String keyword : keywords) {
			System.out.println("\t\t " + keyword);
		}
		
		System.out.println("\t User Story: " + userStory);
		System.out.println("---------------------------------------------------");
	}
	
	public String toString() {
		return "Effort Log with Start Date " + startDate + " , Lifecycle Step " + LCstep + " , Deliverable " + deliverable;
	}
	
	public static void printProjectEffortLogs(String projectName) {
		System.out.println("Printing all effort logs for project: " + projectName);
		for (EffortLog e : Definitions.projectEffortLogs.get(projectName)) {
			e.print();
		}
	}
	
	public static void printAll() {
		System.out.println("Printing all effort logs");
		System.out.println();
	
		for (String projectName : Definitions.projectNames) {
			System.out.println("Project: " + projectName);
			System.out.println();
			EffortLog.printProjectEffortLogs(projectName);
		}
	}
	
	// getter functions
	
	public String getLCStep() {
		return this.LCstep;
	}
	
	public String getDeliverable() {
		return this.deliverable;
	}
	
	// setter functions
	
	public void setLCStep(String LCstep) {
		this.LCstep = LCstep;
	}
	
	public void setDeliverable(String deliverable) {
		this.deliverable = deliverable;
	}
	
}
