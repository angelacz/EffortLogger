package asuHelloWorldJavaFX;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

public class Definitions {
	public static List<String> projectNames;
	public static List<ArrayList<Integer>> projectLCSteps;
	
	public static List<String> LCstepNames;
	public static List<ArrayList<Integer>> LCstepEffortCategories;
	
	public static List<String> effortCategoryNames;
	public static List<ArrayList<String>> effortCategoryChoices;
	public static List<String> effortCategoryChoiceNames;
	
	
	public static void initializeClassVariables() {
		projectNames = new ArrayList<String>(
				Arrays.asList("Business Project", "Development Project"));
		
		projectLCSteps = new ArrayList<ArrayList<Integer>>();
		projectLCSteps.add(new ArrayList<Integer> (
				Arrays.asList(17,18,19,20,21,22,23,24,25,26)));
		projectLCSteps.add(new ArrayList<Integer> (
				Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)));
		
		LCstepNames = new ArrayList<String>(
				Arrays.asList(
						"Problem Understanding","Conceptual Design Plan","Conceptual Design Review",
						"Detailed Design Plan","Detailed Design Prototype","Detailed Design Review",
						"Implementation Plan","Test Case Generation",
						"Solution Specification","Solution Review","Solution Implementation",
						"Unit/System Test","Reflection","Repository Update",
						
						"Planning","Information Gathering","Information Understanding",
						"Verifying","Outlining","Drafting","Finalizaing",
						"Team Meeting","Coach Meeting","Stakeholder Meeting"
						));
		
		LCstepEffortCategories = new ArrayList<ArrayList<Integer>>();
		
//		int[][] LCstepEffortCategoriesArray = {
//				{2,1},{1,3},{2,1},{2,1},{2,1},{1,4},{2,2},{2,2},{1,5},{2,3},{2,4},{2,4},{2,4},
//				{2,4},{2,4},{2,4},{1,1},{2,1},{2,1},{2,1},{2,6},{2,7},{2,8},{2,1},{2,1},{2,1}};
		
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(1,3)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(1,4)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,2)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,2)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(1,5)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,3)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,4)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,4)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,4)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,4)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,4)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,4)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(1,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,6)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,7)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,8)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		LCstepEffortCategories.add(new ArrayList<Integer> (Arrays.asList(2,1)));
		
		effortCategoryNames = new ArrayList<String>(
				Arrays.asList("Plans","Deliverables","Interruptions","Defects"));
		
		effortCategoryChoices = new ArrayList<ArrayList<String>>();
		effortCategoryChoices.add(new ArrayList<String> (
				Arrays.asList(
						"Project Plan","Risk Management Plan","Conceptual Design Plan",
						"Detailed Design Plan","Implementation Plan")));
		effortCategoryChoices.add(new ArrayList<String> (
				Arrays.asList(
						"Conceptual Design","Detailed Design","Test Cases","Solution",
						"Reflection","Outline","Draft","Report")));
		effortCategoryChoices.add(new ArrayList<String> (
				Arrays.asList("Break","Phone","Teammate","Visitor")));
		effortCategoryChoices.add(new ArrayList<String> (
				Arrays.asList(
						"Not specificed","Documentation","Syntax","Build, Package",
						"Assignment","Interface","Checking","Data","Function","System","Environment")));
		
		effortCategoryChoiceNames = new ArrayList<String> ();
		for (int i=0; i<effortCategoryChoices.size(); i++) {
			effortCategoryChoiceNames.addAll(effortCategoryChoices.get(i));
		}
		
	}
	
}

















