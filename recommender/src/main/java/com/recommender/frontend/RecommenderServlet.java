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
	private static final int NUMBER_OF_RECOMMENDATIONS_REQUIRED = 3;
	private static final String JOINEE_TEAM = "team";
	private static final String JOINEE_EXPERIENCE = "experience";
	private static final String JOINEE_SKILLS = "skills";
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
		String skills = request.getParameter(JOINEE_SKILLS);
		String experience = request.getParameter(JOINEE_EXPERIENCE);
		String team = request.getParameter(JOINEE_TEAM);
		List<String> previousOrgsList = Arrays.asList(previousOrgs.split(","));
		List<String> skillsList = Arrays.asList(skills.split(","));
		int exp = Integer.parseInt(experience);

		Joinee newJoinee = new Joinee(name, place, college, previousOrgsList, skillsList, exp, team);

		getRecommendationsAndReturnResponse(newJoinee, response);
	}

	private void getRecommendationsAndReturnResponse(Joinee newJoinee, HttpServletResponse response)
	{
		List<Employee> recommendedEmployees = new ArrayList<Employee>();
		try
		{
			recommendedEmployees = Recommender.getRecommendations(newJoinee, NUMBER_OF_RECOMMENDATIONS_REQUIRED);
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
		String response = "<h3>Recommended employees: </h3><br/><br/>";
		for (Employee recommendedEmployee : recommendedEmployees)
		{
			response = response + recommendedEmployee.toString() + "<br/><br/>";
		}
		return response;
	}
}
