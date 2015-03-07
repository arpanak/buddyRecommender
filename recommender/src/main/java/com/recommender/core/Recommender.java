package com.recommender.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Performs the recommendations.
 * 
 * @author AshwinV
 * 
 */
// TODO make matching less strict - e.g with lucene lookups
// TODO make generic - with configurable priorities
public class Recommender
{

	private static List<Employee> existingEmployees = new ArrayList<Employee>();
	
	static
	{
		existingEmployees = PersistenceManager.getAllEmployees();
		try
		{
			LuceneHelper.buildIndex(existingEmployees);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Recommendation logic:
	 * 
	 * Institute Team Skills Tenure Career Level Engagement score No. of buddies
	 * assigned (one buddy should have max. 3 new joiners tagged for a month)
	 * 
	 */
	public static List<Employee> getRecommendations(Joinee newJoinee, int numRecommendationsRequired) throws Exception
	{
		String institute = newJoinee.getCollege();
		String team = newJoinee.getTeam();
		String graduationYear = newJoinee.getYearOfGraduation()+"";
		Map<String, String> filterMap = new HashMap<String, String>();
		filterMap.put("graduateInstitute", institute);
		filterMap.put("postGraduateInstitute", institute);
		filterMap.put("currentTeam", team);
		filterMap.put("graduateYear", graduationYear);
		filterMap.put("postGraduateYear", graduationYear);
		return LuceneHelper.runQuery(filterMap, numRecommendationsRequired);
	}

}
