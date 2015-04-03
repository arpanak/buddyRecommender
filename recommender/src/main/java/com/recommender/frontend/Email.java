package com.recommender.frontend;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents an email message.
 * 
 * @author ashwinvinod
 *
 */
@Getter
@AllArgsConstructor
public class Email
{
	private String to;
	private String from;
	private String cc;
	private String subject;
	private String mailContent;

}
