package com.recommender.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Data;

/**
 * Represents a configuration setting, such as an email template.
 * 
 * @author ashwinvinod
 *
 */
@Entity
@Data
public class Configuration
{
	public enum CONFIGURATION_TYPE
	{
		ASSIGNEE_EMAIL_TEMPLATE,
		ASSIGNEE_EMAIL_SUBJECT,
		ASSIGNEE_EMAIL_CC,
		PREFERENCE_PRIORITIES
	}

	@Id
	@GeneratedValue
	private Integer id;
	private CONFIGURATION_TYPE configurationType;
	@Lob
	private String content;

}
