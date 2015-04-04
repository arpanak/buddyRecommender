package com.recommender.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.cache.ehcache.management.impl.BeanUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import com.recommender.db.Employee;
import com.recommender.db.Joinee;
import com.recommender.services.RecommenderService;

/**
 * Receives http request containing information on the new employee and returns
 * recommendations in the response.
 * 
 * @author AshwinV
 * 
 */
@Component("recommenderServlet")
public class RecommenderServlet implements HttpRequestHandler
{
	public static final int NUMBER_OF_RECOMMENDATIONS_REQUIRED = 5;
	private static final String JOINEE = "joinee";
	private static final String JOINEE_TEAM = "team";
	private static final String JOINEE_GRADUATION_YEAR = "passoutyear";
	private static final String TEXT_HTML = "text/html";
	private static final String JOINEE_COLLEGE = "college";
	private static final String JOINEE_NAME = "name";
	private static final String OK = "OK";
	private static final String TOTAL_RECORD_COUNT = "TotalRecordCount";
	private static final String RECORDS = "Records";
	private static final String RESULT = "Result";

	@Autowired
	private RecommenderService recommenderService;
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = request.getParameter(JOINEE_NAME);
		String college = request.getParameter(JOINEE_COLLEGE);
		String yearOfGraduation = request.getParameter(JOINEE_GRADUATION_YEAR);
		String team = request.getParameter(JOINEE_TEAM);
		int graduationYear = Integer.parseInt(yearOfGraduation);

		Joinee newJoinee = new Joinee(null, name, "", college, new ArrayList<String>(), new ArrayList<String>(), graduationYear, team, null);
		request.getSession().setAttribute(JOINEE, newJoinee);
		
		getRecommendationsAndReturnResponse(newJoinee, response);
	}
	
	public static void writeResponse(HttpServletResponse response, String responseString)
	{
		PrintWriter responseWriter;
		try
		{
			responseWriter = response.getWriter();
			response.setContentType(TEXT_HTML);
			responseWriter.print(responseString);
			IOUtils.closeQuietly(responseWriter);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void getRecommendationsAndReturnResponse(Joinee newJoinee, HttpServletResponse response)
	{
		List<Employee> recommendedEmployees = new ArrayList<Employee>();
		try
		{
			recommendedEmployees = recommenderService.getRecommendations(newJoinee, NUMBER_OF_RECOMMENDATIONS_REQUIRED);
			writeResponse(response, getResponseAsString(recommendedEmployees));
		}
		catch (Exception e)
		{
			String responseString = "<h3>Recommended employees: </h3>Error occured while processing.";
			writeResponse(response, responseString);
			throw new RuntimeException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private String getResponseAsString(List<Employee> recommendedEmployees)
	{
		JSONArray employeeArray = new JSONArray();
		if (CollectionUtils.isNotEmpty(recommendedEmployees))
		{
			for (Employee recommendedEmployee : recommendedEmployees)
			{
				JSONObject employee = new JSONObject();
				for(Field field : Employee.class.getDeclaredFields())
				{
					field.setAccessible(true);
					String fieldName = field.getName();
					employee.put(fieldName, BeanUtils.getBeanProperty(recommendedEmployee, fieldName));
				}
				employeeArray.add(employee);
			}
		}
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put(RESULT, OK);
		jsonResponse.put(RECORDS, employeeArray);
		jsonResponse.put(TOTAL_RECORD_COUNT, employeeArray.size());
		return jsonResponse.toJSONString();
	}
}
