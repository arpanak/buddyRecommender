package com.recommender.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recommender.db.Employee;
import com.recommender.db.Joinee;
import com.recommender.utilities.LuceneHelper;

/**
 * 
 * Performs the recommendations.
 * 
 * @author AshwinV
 * 
 */
@Service
public class RecommenderService
{

	private static List<Employee> existingEmployees = new ArrayList<Employee>();
	private static Map<String, Float> propertyName2priority = new HashMap<String, Float>();

	@Autowired
	private EmployeeService employeeService;
	
	@PostConstruct
	public void initializeRecommender()
	{
		existingEmployees = employeeService.getAllEmployees();
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
	
	@PreDestroy
	public void closeLuceneIndex()
	{
		LuceneHelper.close();
	}
	
	public List<Employee> getRecommendations(Joinee newJoinee, int numRecommendationsRequired) throws Exception
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

}
