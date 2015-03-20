package com.recommender.frontend;

public class Email
{
	private String to;
	private String from;
	private String cc;
	private String subject;
	private String mailContent;

	public Email(String to, String from, String cc, String subject, String mailContent)
	{
		super();
		this.to = to;
		this.from = from;
		this.cc = cc;
		this.subject = subject;
		this.mailContent = mailContent;
	}

	public String getFrom()
	{
		return from;
	}

	public String getTo()
	{
		return to;
	}

	public String getCc()
	{
		return cc;
	}

	public String getSubject()
	{
		return subject;
	}

	public String getMailContent()
	{
		return mailContent;
	}

}
