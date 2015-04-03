package com.recommender.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
	@Autowired(required = true)
	private MailSender mailSender;

	public void sendMail(Email email)
	{
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(email.getFrom());
		message.setTo(email.getTo());
		if(StringUtils.isNotEmpty(email.getCc()))
		{
			message.setCc(email.getCc());
		}
		message.setSubject(email.getSubject());
		message.setText(email.getMailContent());
		mailSender.send(message);
	}
}
