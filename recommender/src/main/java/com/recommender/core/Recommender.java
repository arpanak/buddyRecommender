package com.recommender.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Performs the recommendations.
 * 
 * @author AshwinV
 * 
 */
public class Recommender
{

	/**
	 * Recommendation logic:
	 * 
	 * 1) Sort by institute 
	 * 2) Check any college match within team 
	 * 3) No match found, checked same year pass out 
	 */
	public List<Employee> getRecommendations(Employee newJoinee) throws Exception
	{
		List<Employee> recommendations = new ArrayList<Employee>();
		List<Employee> existingEmployees = PersistenceManager.getAllEmployees();
		for (Employee existingEmployee : existingEmployees)
		{
			//perform recommendation logic
		}
		return recommendations;
	}
}