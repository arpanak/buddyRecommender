package com.recommender.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService
{
	@Autowired(required = true)
	private MailSender mailSender;

	public void sendMail(String from, String to, String cc, String subject, String msg)
	{
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		if(StringUtils.isNotEmpty(cc))
		{
			message.setCc(cc);
		}
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}
}
