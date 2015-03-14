package com.recommender.frontend;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.recommender.utilities.SuggestionHelper;

/**
 * This servlet receives a string of characters and a field name. It finds a
 * list of possible matches. E.g for property=currentTeam and value=home, would
 * return "home union"
 * 
 * @author ashwinvinod
 *
 */
public class Suggester extends HttpServlet
{

	private static final String VALUE = "value";
	private static final String PROPERTY = "property";
	private static final long serialVersionUID = -1516039881569772830L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String property = request.getParameter(PROPERTY);
		String value = request.getParameter(VALUE);
		String suggestions;
		try
		{
			suggestions = SuggestionHelper.getSuggestions(property, value, 10);
			RecommenderServlet.writeResponse(response, suggestions);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		doPost(request, response);
	}
}
