package com.recommender.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
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

	@Autowired
	private RecommenderService recommenderService;
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String name = request.getParameter(JOINEE_NAME);
		String college = request.getParameter(JOINEE_COLLEGE);
		String yearOfGraduation = request.getParameter(JOINEE_GRADUATION_YEAR);
		String team = request.getParameter(JOINEE_TEAM);
		int graduationYear = Integer.parseInt(yearOfGraduation);

		Joinee newJoinee = new Joinee(name, "", college, new ArrayList<String>(), new ArrayList<String>(), graduationYear, team);
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

	private String getResponseAsString(List<Employee> recommendedEmployees)
	{
		String response = "";
		if (CollectionUtils.isNotEmpty(recommendedEmployees))
		{
			response = "<h3>Recommended buddies: </h3><br/>";
			response += "<table>";
			response += "<th><td><b>No.</b></td><td><b>Name</b></td><td><b>Current Team</b></td><td><b>Colleges</b></td><td><b>Graduate Year</b></td><td></td></th>";
			int i = 1;
			for (Employee recommendedEmployee : recommendedEmployees)
			{
				response += "<tr class = \"row\">";
				response +=  "<td>" + i + "</td><td>" + recommendedEmployee.getName() + "</td><td>"
						+ recommendedEmployee.getCurrentTeam() + "</td><td>"
						+ recommendedEmployee.getPostGraduateInstitute()
						+ "</td><td>" + recommendedEmployee.getPostGraduateYear() + "</td><td><a id='"+recommendedEmployee.getId()+"' onclick=\"$(this).addClass('open-window');$( '#dialog' ).dialog( 'open' )\" href=\"#\">Assign buddy</a></td>";
				response += "</tr>";
				i++;
			}
			response += "</table>";
		}
		else
		{
			response = "<h3>Recommended employees: </h3><br/><br/>No employees found.";
		}
		return response;
	}
}
