package com.recommender.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.recommender.frontend.RecommenderServlet;

/**
 * 
 * Performs the recommendations.
 * 
 * @author AshwinV
 * 
 */
public class Recommender
{

	private static List<Employee> existingEmployees = new ArrayList<Employee>();
	// TODO priorities could be configured in properties file
	private static Map<String, Float> propertyName2priority = new HashMap<String, Float>();

	public static void initializeRecommender()
	{
		existingEmployees = PersistenceManager.getAllEmployees();
		try
		{
			propertyName2priority.put("currentTeam", 40f);
			propertyName2priority.put("graduateInstitute", 20f);
			propertyName2priority.put("postGraduateInstitute", 20f);
			propertyName2priority.put("graduateYear", 8f);
			propertyName2priority.put("postGraduateYear", 8f);

			LuceneHelper.initialize(existingEmployees, propertyName2priority);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}	
	}
	
	public static List<Employee> getRecommendations(Joinee newJoinee, int numRecommendationsRequired) throws Exception
	{
		String institute = newJoinee.getCollege();
		String team = newJoinee.getTeam();
		String graduationYear = newJoinee.getYearOfGraduation() + "";
		Map<String, String> filterMap = new HashMap<String, String>();
		filterMap.put("graduateInstitute", institute);
		filterMap.put("postGraduateInstitute", institute);
		filterMap.put("currentTeam", team);
		filterMap.put("graduateYear", graduationYear);
		filterMap.put("postGraduateYear", graduationYear);
		return LuceneHelper.runQuery(filterMap, numRecommendationsRequired);
	}

	public static List<Employee> getRecommendations(String college, String graduationYear, String team) throws Exception
	{
		Joinee newJoinee = new Joinee("", "", college, new ArrayList<String>(), new ArrayList<String>(), Integer.parseInt(graduationYear), team);
		return Recommender.getRecommendations(newJoinee, RecommenderServlet.NUMBER_OF_RECOMMENDATIONS_REQUIRED);
	}
}
