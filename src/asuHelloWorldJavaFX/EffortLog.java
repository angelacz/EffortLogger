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

import java.util.LinkedList;

public class EffortLog {
	
	static LinkedList<EffortLog> EffortLogs = new LinkedList<EffortLog>();
	
	private long startTime;
	private long endTime;
	private long elapsedTime;
	
	private String projectName;
	private String LCstep;
	private String effortCategory;
	private String deliverable;
	
	private String keywords;
	private String userStory;
	
	// other functions
	public void save() {
		EffortLogs.add(this);
		
		System.out.println("Saved effort log! Printing all logs: ");
		System.out.println();
		
		printAll();
	}
	
	public void print() {
		System.out.println("Effort log with start time " + this.getStartTime() + " and elapsed time " + this.getElapsedTime());
		
		System.out.println("\t Project: " + this.getProjectName());
		System.out.println("\t Lifecycle step: " + this.getLCStep());
		System.out.println("\t Effort category: " + this.getEffortCategory());
		System.out.println("\t Deliverable: " + this.getDeliverable());
		System.out.println();
		
		System.out.println("\t Keywords: " + this.getKeywords());
		System.out.println("\t Deliverable: " + this.getUserStory());
		System.out.println();
	}
	
	public void printAll() {
		System.out.println("Number of effort logs: " + EffortLogs.size());
		System.out.println();
		
		for (int i=0; i < EffortLogs.size(); i++) {
			EffortLogs.get(i).print();
		}
	}
	
	// getter functions
	public long getStartTime() {
		return this.startTime;
	}
	
	public long getEndTime() {
		return this.endTime;
	}
	
	public long getElapsedTime() {
		return this.elapsedTime;
	}
	
	public String getProjectName() {
		return this.projectName;
	}
	
	public String getLCStep() {
		return this.LCstep;
	}
	
	public String getEffortCategory() {
		return this.effortCategory;
	}
	
	public String getDeliverable() {
		return this.deliverable;
	}
	
	public String getKeywords() {
		return this.keywords;
	}
	
	public String getUserStory() {
		return this.userStory;
	}
	
	// setter functions
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public void setLCStep(String LCstep) {
		this.LCstep = LCstep;
	}
	
	public void setEffortCategory(String effortCategory) {
		this.effortCategory = effortCategory;
	}
	
	public void setDeliverable(String deliverable) {
		this.deliverable = deliverable;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public void setUserStory(String userStory) {
		this.userStory = userStory;
	}
	
}
