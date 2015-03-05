package com.recommender.frontend;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import com.recommender.core.Employee;
import com.recommender.core.Joinee;
import com.recommender.core.Recommender;

/**
 * Receives http request containing information on the new employee and returns
 * recommendations in the response.
 * 
 * @author AshwinV
 * 
 */
public class RecommenderServlet extends HttpServlet
{
	private static final String TEXT_HTML = "text/html";
	private static final String PREVIOUS_ORGANIZATIONS = "previousOrganizations";
	private static final String JOINEE_COLLEGE = "college";
	private static final String JOINEE_PLACE = "place";
	private static final String JOINEE_NAME = "name";
	private static final long serialVersionUID = -315225148153801990L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter(JOINEE_NAME);
		String place = request.getParameter(JOINEE_PLACE);
		String college = request.getParameter(JOINEE_COLLEGE);
		String previousOrgs = request.getParameter(PREVIOUS_ORGANIZATIONS);
		List<String> previousOrgsList = Arrays.asList(previousOrgs.split(","));
		Joinee newJoinee = new Joinee(name, place, college, previousOrgsList);
		
		List<Employee> recommendedEmployees = new ArrayList<Employee>();
		try
		{
			recommendedEmployees = Recommender.getRecommendations(newJoinee);
			PrintWriter responseWriter = response.getWriter();
			response.setContentType(TEXT_HTML);
			responseWriter.print(getResponseAsString(recommendedEmployees));
			IOUtils.closeQuietly(responseWriter);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}

	private String getResponseAsString(List<Employee> recommendedEmployees)
	{
		String response = "<h2>Recommended employees: </h2><br/><br/>";
		for(Employee recommendedEmployee : recommendedEmployees)
		{
			response = response + recommendedEmployee.toString() + "<br/><br/>";
		}
		return response;
	}
}
