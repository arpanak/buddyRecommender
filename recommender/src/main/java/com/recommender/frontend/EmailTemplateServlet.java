package com.recommender.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import com.recommender.services.ConfigurationService;

@Component("emailTemplateServlet")
public class EmailTemplateServlet implements HttpRequestHandler
{

	private static final String TEXT_HTML = "text/html";
	private static final String FORM_TYPE = "formType";
	private static final String APPLICATION_JSON = "application/json";
	private static final String JOINEE_NAME = "joineeName";
	private static final String EMPLOYEE_ID = "employeeId";
	private static final String MAIL_CONTENT = "mailContent";
	private static final String SUBJECT = "subject";
	private static final String CC = "cc";

	@Autowired
	private ConfigurationService configurationService;

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String formType = request.getParameter(FORM_TYPE);
		if("assigneeTemplate".equals(formType))
		{
			String cc = request.getParameter(CC);
			String subject = request.getParameter(SUBJECT);
			String mailContent = request.getParameter(MAIL_CONTENT);
			
			configurationService.saveAssigneeTemplate(cc, subject, mailContent);
			
			PrintWriter out = response.getWriter();
			response.setContentType(TEXT_HTML);
			out.print("Template details saved");
			IOUtils.closeQuietly(out);
		}
		else
		{
			String employeeId = request.getParameter(EMPLOYEE_ID);
			String joineeName = request.getParameter(JOINEE_NAME);
			JSONObject emailData = configurationService.getAssigneeEmailData(employeeId, joineeName);
			
			PrintWriter out = response.getWriter();
			response.setContentType(APPLICATION_JSON);
			out.print(emailData.toJSONString());
			IOUtils.closeQuietly(out);
		}
		
	}

}
