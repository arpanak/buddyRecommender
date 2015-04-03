package com.recommender.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import com.recommender.db.Employee;
import com.recommender.db.Joinee;
import com.recommender.services.AssignmentService;
import com.recommender.services.AssignmentService.ASSIGNMENT_STATUS;
import com.recommender.services.EmployeeService;

/**
 * This servlet handles requests related to assignment of buddies.
 * 
 * @author ashwinvinod
 *
 */
@Component("assignmentServlet")
public class AssignmentServlet implements HttpRequestHandler
{
	private static final String JOINEE = "joinee";
	private static final String SELECTED_EMPLOYEE_ID = "selectedEmployeeId";
	private static final String FROM_ADDRESS = "buddyRecommender@gmail.com";
	private static final String MAIL_CONTENT = "mailContent";
	private static final String SUBJECT = "subject";
	private static final String CC = "cc";
	private static final String TO = "to";

	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private EmployeeService employeeService;

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String to = request.getParameter(TO);
		String cc = request.getParameter(CC);
		String subject = request.getParameter(SUBJECT);
		String mailContent = request.getParameter(MAIL_CONTENT);
		String employeeId = request.getParameter(SELECTED_EMPLOYEE_ID);

		Email emailToSend = new Email(to, FROM_ADDRESS, cc, subject, mailContent);
		Joinee joinee = (Joinee) request.getSession().getAttribute(JOINEE);
		Employee buddyToAssign = employeeService.findEmployeeById(employeeId);

		ASSIGNMENT_STATUS assignmentResult = assignmentService.assignAndMailBuddy(buddyToAssign, joinee, emailToSend);

		PrintWriter out = response.getWriter();
		out.println(assignmentResult.getStatus());
		if (ASSIGNMENT_STATUS.SUCCESS.equals(assignmentResult))
		{
			out.println("Email sent to assigned buddy at " + to);
		}

	}

}
