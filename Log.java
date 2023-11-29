package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.util.concurrent.TimeUnit;

public class Log {
	
	// class variables
	static TimeUnit timeUnit= TimeUnit.SECONDS;
	
	// timing info
	long startTime;
	long endTime;	
	long elapsedTime;
	
	Date startDate;
	Date endDate;
	
	// log attributes
	String projectName;
	String effortCategory;
	
	List<String> keywords;
	String userStory;
	
	// constructor
	public Log() {
		this.keywords = new ArrayList<>();
		this.userStory = "";
	}
	
	// print functions
	
	public static void printAll() {
		System.out.println("Printing all logs");
		System.out.println();
	
		for (String projectName : Definitions.projectNames) {
			System.out.println("Printing all logs for project: " + projectName);
			System.out.println();
			
			EffortLog.printProjectEffortLogs(projectName);
			DefectLog.printProjectDefectLogs(projectName);
		}
	}
	
	public void print(){};
	
	// getter functions
	
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
	
	public long getElapsedTimeAsTimeUnit() {
		long elapsedTime = this.getElapsedTime();
		
		if (Log.timeUnit == TimeUnit.HOURS) {
			return TimeUnit.MILLISECONDS.toHours(elapsedTime);
		}
		else if (Log.timeUnit == TimeUnit.MINUTES) {
			return TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
		}
		else {
			return TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
		}	
	}
	
	// returns elapsed time as long array [hr, min, sec]
	// instance version
	public long[] getElapsedTimeAsHourMinSec() {
		return getMillisAsHourMinSec(this.elapsedTime);
	}
	
	// returns elapsed time as long array [hr, min, sec]
	// class version
	public static long[] getMillisAsHourMinSec(long millis) {
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
	
	public String getProjectName() {
		return this.projectName;
	}
	
	public String getEffortCategory() {
		return this.effortCategory;
	}
	
	public List<String> getKeywords() {
		return this.keywords;
	}
	
	public String getUserStory() {
		return this.userStory;
	}
	
	// setter (class) functions
	
	public static void setLogTimeUnit(TimeUnit newLogTimeUnit) {
		timeUnit = newLogTimeUnit;
	}
	
	
	// setter (instance) functions 
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public void setStartDate() {
		this.startDate = new Date();
		this.startTime = this.startDate.getTime();
	}
	
	public void setStartDate(Date date) {
		this.startDate = date;
		this.startTime = date.getTime();
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
	
	public void setEffortCategory(String effortCategory) {
		this.effortCategory = effortCategory;
	}
	
	// keywords is CSV string
	public void setKeywords(String keywords) {
		this.keywords = Arrays.stream(keywords.split(",")).map(x->x.trim()).collect(Collectors.toList());
	}
	
	public void setUserStory(String userStory) {
		this.userStory = userStory;
	}

}