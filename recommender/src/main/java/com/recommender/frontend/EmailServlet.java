package com.recommender.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import com.recommender.services.MailService;

@Component("emailServlet")
public class EmailServlet extends HttpServlet implements HttpRequestHandler
{
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServlet.class);
	
	private static final String FROM_ADDRESS = "buddyRecommender@gmail.com";
	private static final String MAIL_CONTENT = "mailContent";
	private static final String SUBJECT = "subject";
	private static final String CC = "cc";
	private static final String TO = "to";

	private static final long serialVersionUID = 1637951075330029552L;

	@Autowired
	private MailService mailService;
	
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String to = request.getParameter(TO);
		String cc = request.getParameter(CC);
		String subject = request.getParameter(SUBJECT);
		String mailContent = request.getParameter(MAIL_CONTENT);
		
		PrintWriter out = response.getWriter();
		try
		{
			mailService.sendMail(FROM_ADDRESS, to, cc, subject, mailContent);
			out.println("Email sent to assigned buddy at "+to);
		}
		catch(Exception ex)
		{
			out.println("Error sending mail");
			LOGGER.error("Error sending mail - {}",ex.getMessage());
		}
		
	}

}
