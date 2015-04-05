package com.recommender.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(BuddyServlet.class);

	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static final String BUDDY_ASSIGNED_ON = "buddyAssignedOn";
	private static final String ASSIGNEE_ID = "assigneeId";
	private static final String ASSIGNEE_NAME = "assigneeName";
	private static final String BUDDY_NAME = "buddyName";
	private static final String BUDDY_ID = "buddyId";
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
		for (Employee buddy : buddies)
		{
			for (Joinee joinee : buddy.getAssignedJoinees())
			{
				DateFormat formatter = new SimpleDateFormat(DD_MM_YYYY);
				String buddyAssignedOn = formatter.format(joinee.getBuddyAssignedOn());
				
				JSONObject entry = new JSONObject();
				entry.put(BUDDY_ID, buddy.getId());
				entry.put(BUDDY_NAME, buddy.getName());
				entry.put(ASSIGNEE_NAME, joinee.getName());
				entry.put(ASSIGNEE_ID, joinee.getId());
				entry.put(BUDDY_ASSIGNED_ON, buddyAssignedOn);
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
