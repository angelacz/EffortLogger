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

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.concurrent.TimeUnit;

public class EffortLog {
	
	// class variables
	static LinkedList<EffortLog> EffortLogs = new LinkedList<EffortLog>();
	static int numEffortLogs = 0;
	static long avgElapsedTime = 0;
	static TimeUnit logTimeUnit= TimeUnit.SECONDS;
	
	// timing info
	private long startTime;
	private long endTime;	
	private long elapsedTime;
	
	private Date startDate;
	private Date endDate;
	
	// log attributes
	private String projectName;
	private String LCstep;
	private String effortCategory;
	private String deliverable;
	
	private List<String> keywords;
	private String userStory;
	

	public void save() {
		// update class variables
		EffortLogs.add(this);
		updateNumEffortLogs();
		updateAvgElapsedTime();
		
		// print all log info
		System.out.println("Saved effort log! Printing all logs: ");
		System.out.println();
		printAll();
	}
	
	public void print() {
		System.out.println("Effort log");
		System.out.println("Start date: " + this.getStartDate());
		System.out.println("End date: " + this.getEndDate());
		System.out.println("Elapsed time: " + this.getElapsedTimeAsLogTimeUnit() + " " + logTimeUnit);
		
		System.out.println("\t Project: " + this.getProjectName());
		System.out.println("\t Lifecycle step: " + this.getLCStep());
		System.out.println("\t Effort category: " + this.getEffortCategory());
		System.out.println("\t Deliverable: " + this.getDeliverable());
		System.out.println();
		
		System.out.println("\t Keywords: ");
		
		List<String> keywordsList = this.getKeywords();
		for (int i=0; i<keywordsList.size(); i++) {
			System.out.println("\t\t " + keywordsList.get(i));
		}
		
		System.out.println("\t User Story: " + this.getUserStory());
		System.out.println();
	}
	
	public static void printAll() {
		System.out.println("Number of effort logs: " + numEffortLogs);
		System.out.println();
		
		for (int i=0; i < numEffortLogs; i++) {
			EffortLogs.get(i).print();
		}
	}
	
	// getter functions
	
	// returns elapsed time as long array [hr, min, sec]
	public long[] getHourMinSec() {
		return getHourMinSec(this.elapsedTime);
	}
	
	public static long[] getHourMinSec(long millis) {
		long totalHours, totalMinutes, totalSeconds;
		long hours, minutes, seconds;
		
		totalHours = TimeUnit.MILLISECONDS.toHours(millis);
		totalMinutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		totalSeconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		
		seconds = totalSeconds - TimeUnit.MINUTES.toSeconds(totalMinutes);
		minutes = totalMinutes - TimeUnit.HOURS.toMinutes(totalHours);
		hours = totalHours;
		
		long[] hourMinSec = {hours, minutes, seconds};
		return hourMinSec;
	}
	
	public long getElapsedTimeAsLogTimeUnit() {
		long elapsedTime = this.getElapsedTime();
		
		if (EffortLog.logTimeUnit == TimeUnit.HOURS) {
			return TimeUnit.MILLISECONDS.toHours(elapsedTime);
		}
		else if (EffortLog.logTimeUnit == TimeUnit.MINUTES) {
			return TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
		}
		else {
			return TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
		}	
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
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
	
	public List<String> getKeywords() {
		return this.keywords;
	}
	
	public String getUserStory() {
		return this.userStory;
	}
	
	// setter functions
	public static void updateNumEffortLogs() {
		numEffortLogs = EffortLogs.size();
	}
	
	public void updateAvgElapsedTime() {
		avgElapsedTime += (this.getElapsedTime() - avgElapsedTime)/numEffortLogs;
	}
	
	public void setStartDate() {
		this.startDate = new Date();
		this.startTime = this.startDate.getTime();
	}
	
	public void setEndDate() {
		this.endDate = new Date();
		this.endTime = this.endDate.getTime();
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	// set elapsed time as millis and hr/min/sec format
	public void setElapsedTime() {
		this.elapsedTime = this.getEndTime() - this.getStartTime();
	}
	
	public static void setDefaultLogTimeUnit() {
		long[] hourMinSec = getHourMinSec(avgElapsedTime);
		
		if (hourMinSec[0] > 0) {
			EffortLog.logTimeUnit = TimeUnit.HOURS;
		} 
		else if (hourMinSec[1] > 0) {
			EffortLog.logTimeUnit = TimeUnit.MINUTES;
		}
		else {
			EffortLog.logTimeUnit = TimeUnit.SECONDS;
		}
	}
	
	public static void setLogTimeUnit(TimeUnit newLogTimeUnit) {
		EffortLog.logTimeUnit = newLogTimeUnit;
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
	
	// keywords is CSV string
	public void setKeywords(String keywords) {
		this.keywords = Arrays.stream(keywords.split(",")).map(x->x.trim()).collect(Collectors.toList());
	}
	
	public void setUserStory(String userStory) {
		this.userStory = userStory;
	}
	
}
