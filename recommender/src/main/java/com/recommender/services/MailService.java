package com.recommender.services;

import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.recommender.frontend.Email;

/**
 * Email sending endpoint.
 * 
 * @author ashwinvinod
 *
 */
@Service
public class MailService
{
	private static final String TEXT_HTML = "text/html";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
	
	@Autowired(required = true)
	private JavaMailSender mailSender;

	public void sendMail(Email email) throws Exception
	{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom(email.getFrom());
		helper.setTo(email.getTo());
		if (StringUtils.isNotEmpty(email.getCc()))
		{
			helper.setCc(email.getCc());
		}
		helper.setSubject(email.getSubject());
		message.setContent(email.getMailContent(), TEXT_HTML);
		
		mailSender.send(message);
		LOGGER.info("Email successfully dispatched to {}", email.getTo());
	}
}
