package asuHelloWorldJavaFX;

import java.util.ArrayList;
import java.util.List;

public class DefectLog extends Log {
	
	// class variables
	static List<String> DefectCategories = new ArrayList<String>(List.of(
			"Not specificed","Documentation","Syntax","Build, Package",
			"Assignment","Interface","Checking","Data","Function","System","Environment"));
	
	static String effortCategory = Definitions.effortCategoryNames.get(3);
	
	// defect log attributes
	String LCstepInjected;
	String LCstepRemoved;
	String defectCategory;
	
	public DefectLog() {
		super();
	}
	
	public void save() {
		Definitions.projectDefectLogs.get(this.projectName).add(this);
		
		// print defect log info
		System.out.println("Saved Defect Log!");
		System.out.println();
		print();
	}
	
	// print functions
	
	public void print() {
		System.out.println("---------------------------------------------------");
		System.out.println("Defect Log");
		System.out.println("Start date: " + startDate);
		System.out.println("End date: " + endDate);
		System.out.println("Elapsed time: " + this.getElapsedTimeAsTimeUnit() + " " + Log.timeUnit);
		
		System.out.println("\t Project: " + projectName);
		System.out.println("\t Lifecycle step injected: " + LCstepInjected);
		System.out.println("\t Lifecycle step removed: " + LCstepRemoved);
		System.out.println("\t Defect category: " + defectCategory);
		System.out.println();
		
		System.out.println("\t Keywords: ");
		for (String keyword : keywords) {
			System.out.println("\t\t " + keyword);
		}
		
		System.out.println("\t User Story: " + userStory);
		System.out.println("---------------------------------------------------");
	}
	
	public static void printProjectDefectLogs(String projectName) {
		System.out.println("Printing all defect logs for project: " + projectName);
		for (DefectLog d : Definitions.projectDefectLogs.get(projectName)) {
			d.print();
		}
	}
	
	public static void printAll() {
		System.out.println("Printing all defect logs");
		System.out.println();
	
		for (String projectName : Definitions.projectNames) {
			System.out.println("Project: " + projectName);
			System.out.println();
			printProjectDefectLogs(projectName);
		}
	}
	
	public String toString() {
		return "Defect Log with Start Date " + startDate + " , Defect Category " + defectCategory;
	}
	
	// getter functions
	
	public String getLCstepInjected() {
		return this.LCstepInjected;
	}
	
	public String getLCstepRemoved() {
		return this.LCstepRemoved;
	}
	
	public String getDefectCategory() {
		return this.defectCategory;
	}
	
	// setter functions
	
	public void setLCstepInjected(String LCstep) {
		this.LCstepInjected = LCstep;
	}
	
	public void setLCstepRemoved(String LCstep) {
		this.LCstepRemoved = LCstep;
	}
	
	public void setDefectCategory(String defectCategory) {
		this.defectCategory = defectCategory;
	}
	
	
}
