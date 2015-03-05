package com.recommender.frontend;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Receives http request containing information on the new employee and returns
 * recommendations in the response.
 * 
 * @author AshwinV
 * 
 */
public class RecommenderServlet extends HttpServlet
{
	private static final long serialVersionUID = -315225148153801990L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}

}
