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
	private static final String OK = "OK";
	private static final String TOTAL_RECORD_COUNT = "TotalRecordCount";
	private static final String RECORDS = "Records";
	private static final String RESULT = "Result";
	
	@Autowired
	private EmployeeService employeeService;
	
	@SuppressWarnings("unchecked")
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Employee> buddies = employeeService.getAllAssignedEmployees();
		JSONArray buddyRecords = new JSONArray();
		for(Employee buddy : buddies)
		{
			for(Joinee joinee : buddy.getAssignedJoinees())
			{
				JSONObject entry = new JSONObject();
				entry.put("buddyId", buddy.getId());
				entry.put("buddyName", buddy.getName());
				entry.put("assigneeName", joinee.getName());
				entry.put("assigneeId", joinee.getId());
				buddyRecords.add(entry);
			}
		}

		PrintWriter out = response.getWriter();
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put(RESULT, OK);
		jsonResponse.put(RECORDS, buddyRecords);
		jsonResponse.put(TOTAL_RECORD_COUNT, buddyRecords.size());
		out.print(jsonResponse.toJSONString());
		IOUtils.closeQuietly(out);
	}

}
