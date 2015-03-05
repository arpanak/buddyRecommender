package com.recommender.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

/**
 * 
 * Performs the recommendations.
 * 
 * @author AshwinV
 * 
 */
//TODO make matching less strict - e.g with lucene lookups
//TODO make generic - with configurable priorities
public class Recommender
{

	private static List<Employee> existingEmployees = new ArrayList<Employee>();

	static
	{
		existingEmployees = PersistenceManager.getAllEmployees();
	}

	/**
	 * Recommendation logic:
	 * 
	 * Institute 
	 * Team 
	 * Skills 
	 * Tenure 
	 * Career Level 
	 * Engagement score 
	 * No. of buddies assigned (one buddy should have max. 3 new joiners tagged for a month)
	 * 
	 */
	public static List<Employee> getRecommendations(Joinee newJoinee, int numRecommendationsRequired) throws Exception
	{
		List<Employee> recommendations = new ArrayList<Employee>();
		String institute = newJoinee.getCollege();
		String team = newJoinee.getTeam();
		recommendations = filterEmployeesByCollege(existingEmployees, institute);
		recommendations = filterEmployeesByTeam(recommendations, team); 
		if(recommendations.size()>numRecommendationsRequired)
		{
			return recommendations.subList(0, numRecommendationsRequired);
		}
		else
		{
			return recommendations;
		}
	}

	@SuppressWarnings("unchecked")
	private static List<Employee> filterEmployeesByTeam(List<Employee> employees, final String team)
	{
		Collection<Employee> filteredList = CollectionUtils.select(employees, new Predicate()
		{
			public boolean evaluate(Object object)
			{
				Employee employee = (Employee) object;
				if (team.equalsIgnoreCase(employee.getCurrentTeam()))
				{
					return true;
				}
				return false;
			}
		});
		return (List<Employee>) filteredList;
	}
	
	@SuppressWarnings("unchecked")
	private static List<Employee> filterEmployeesByCollege(List<Employee> employees, final String college)
	{
		Collection<Employee> filteredList = CollectionUtils.select(employees, new Predicate()
		{
			public boolean evaluate(Object object)
			{
				Employee employee = (Employee) object;
				if (college.equalsIgnoreCase(employee.getGraduateInstitute())
						|| college.equalsIgnoreCase(employee.getPostGraduateInstitute()))
				{
					return true;
				}
				return false;
			}
		});
		return (List<Employee>) filteredList;
	}
}
