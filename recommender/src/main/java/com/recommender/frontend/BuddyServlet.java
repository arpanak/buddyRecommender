package com.recommender.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import com.recommender.db.Employee;
import com.recommender.db.Joinee;
import com.recommender.services.EmployeeService;

/**
 * Servlet endpoint for managing assigned buddies
 * 
 * @author ashwinvinod
 *
 */
@Component("buddyServlet")
public class BuddyServlet implements HttpRequestHandler
{
	@Autowired
	private EmployeeService employeeService;
	
	@SuppressWarnings("unchecked")
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Employee> buddies = employeeService.getAllAssignedEmployees();
		JSONArray jsonResponse = new JSONArray();
		for(Employee buddy : buddies)
		{
			for(Joinee joinee : buddy.getAssignedJoinees())
			{
				JSONObject entry = new JSONObject();
				entry.put("buddyId", buddy.getId());
				entry.put("buddyName", buddy.getName());
				entry.put("assigneeName", joinee.getName());
				entry.put("assigneeId", joinee.getId());
				jsonResponse.add(entry);
			}
		}

		PrintWriter out = response.getWriter();
		out.print(jsonResponse.toJSONString());
		IOUtils.closeQuietly(out);
	}

}
