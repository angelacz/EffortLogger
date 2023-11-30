package asuHelloWorldJavaFX;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// All Maps are implemented as HashMaps, all Lists are implemented as ArrayLists

public class Definitions {
	// List of project names
	static List<String> projectNames; 
	// Maps project name -> ordered list of lifecycle steps
	static Map<String, ArrayList<String>> projectToLCSteps;
	
	// Maps project name -> list of effort logs
	static Map<String, ArrayList<EffortLog>> projectEffortLogs;
	// Maps project name -> list of defect logs
	static Map<String, ArrayList<DefectLog>> projectDefectLogs;
	
	// List of lifecycle step names
	static List<String> LCstepNames;
	// Maps lifecycle step -> ordered list [default effort category, default effort category choice]
	static Map<String, ArrayList<String>> LCstepToDefaultEffortCategory;
	
	// List of effort category names
	static List<String> effortCategoryNames;
	// Maps effort category -> list of effort category choices
	static Map<String, ArrayList<String>> effortCategoryToChoices;
	// List of all effort category choices
	static List<String> effortCategoryChoiceNames;
	
	
	public static void initializeClassVariables() {
		// initialize projectNames
		projectNames = new ArrayList<String>(Arrays.asList("Business Project", "Development Project"));
		
		// initialize projectEffortLogs
		projectEffortLogs = new HashMap<>();
		projectEffortLogs.put(projectNames.get(0), new ArrayList<EffortLog>());
		projectEffortLogs.put(projectNames.get(1), new ArrayList<EffortLog>());
		
		// initialize projectDefectLogs
		projectDefectLogs = new HashMap<>();
		projectDefectLogs.put(projectNames.get(0), new ArrayList<DefectLog>());
		projectDefectLogs.put(projectNames.get(1), new ArrayList<DefectLog>());
		
		// initialize LCstepNames
		LCstepNames = new ArrayList<String>(
				Arrays.asList(
					"Problem Understanding","Conceptual Design Plan","Requirements",
					"Conceptual Design","Conceptual Design Review",
					"Detailed Design Plan","Detailed Design Prototype","Detailed Design Review",
					"Implementation Plan","Test Case Generation",
					"Solution Specification","Solution Review","Solution Implementation",
					"Unit/System Test","Reflection","Repository Update",
					
					"Planning","Information Gathering","Information Understanding",
					"Verifying","Outlining","Drafting","Finalizaing",
					"Team Meeting","Coach Meeting","Stakeholder Meeting"
				));
		
		// initialize projectToLCSteps
		projectToLCSteps = new HashMap<>();
		projectToLCSteps.put(projectNames.get(0), new ArrayList<String>(LCstepNames.subList(16,26)));
		projectToLCSteps.put(projectNames.get(1), new ArrayList<String>(LCstepNames.subList(0,15))); 
		
		// initialize effortCategoryNames
		effortCategoryNames = new ArrayList<String>(Arrays.asList("Plans","Deliverables","Interruptions","Defects"));
		effortCategoryToChoices = new HashMap<>();
		
		// initialize effortCategoryToChoices
		effortCategoryToChoices.put(effortCategoryNames.get(0), new ArrayList<>(List.of(
				"Project Plan","Risk Management Plan","Conceptual Design Plan",
				"Detailed Design Plan","Implementation Plan")));
		
		effortCategoryToChoices.put(effortCategoryNames.get(1), new ArrayList<>(List.of(
				"Conceptual Design","Detailed Design","Test Cases","Solution",
				"Reflection","Outline","Draft","Report")));
		
		effortCategoryToChoices.put(effortCategoryNames.get(2), new ArrayList<>(List.of(
				"Break","Phone","Teammate","Visitor")));
		
		// initialize effortCategoryChoiceNames
		effortCategoryChoiceNames = new ArrayList<String> ();
		
		for (String effortCategory : effortCategoryToChoices.keySet()) {
			effortCategoryChoiceNames.addAll(effortCategoryToChoices.get(effortCategory));
		}
		
		// initialize LCstepToDefaultEffortCategory
		LCstepToDefaultEffortCategory = new HashMap<>();
		
		int[][] LCstepToDefaultEffortCategoryArray = {
				{1,0},{0,2},{1,0},{1,0},{1,0},{0,3},{1,1},{1,1},{0,4},{1,2},{1,3},{1,3},{1,3},
				{1,3},{1,3},{1,3},{0,0},{1,0},{1,0},{1,0},{1,5},{1,6},{1,7},{1,0},{1,0},{1,0}};
		
		for (int i=0; i<LCstepNames.size(); i++) {
			int effortCategoryIdx = LCstepToDefaultEffortCategoryArray[i][0];
			int deliverableIdx = LCstepToDefaultEffortCategoryArray[i][1];
			
			String defaultEffortCategory = effortCategoryNames.get(effortCategoryIdx);
			String defaultDeliverable = effortCategoryToChoices.get(defaultEffortCategory).get(deliverableIdx);
			
			ArrayList<String> effortCategories = new ArrayList<>(List.of(defaultEffortCategory, defaultDeliverable));
			LCstepToDefaultEffortCategory.put(LCstepNames.get(i), effortCategories);
		}
		
		// TESTING DEFECT LOGS
		
//		String businessProject = projectNames.get(0);
//		ArrayList<String> businessLCSteps = projectToLCSteps.get(businessProject);
//		
//		DefectLog businessDefectLog1 = new DefectLog();
//		businessDefectLog1.setProjectName(businessProject);
//		
//		DefectLog businessDefectLog2 = new DefectLog();
//		businessDefectLog2.setProjectName(businessProject);
//		businessDefectLog2.setDefectCategory(DefectLog.DefectCategories.get(2));
//		businessDefectLog2.setLCstepInjected(businessLCSteps.get(1));
//		
//		projectDefectLogs.get(businessProject).add(businessDefectLog1);
//		projectDefectLogs.get(businessProject).add(businessDefectLog2);
			
	}
}

















