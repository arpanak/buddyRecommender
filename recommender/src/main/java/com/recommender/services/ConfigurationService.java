package com.recommender.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recommender.db.Configuration;
import com.recommender.db.Configuration.CONFIGURATION_TYPE;
import com.recommender.db.Employee;
import com.recommender.repositories.ConfigurationRepository;

@Service
public class ConfigurationService
{
	private static final String SUBJECT = "subject";
	private static final String TO_ADDRESS = "toAddress";
	private static final String EMAIL_CONTENT = "emailContent";
	private static final String JOINEE_NAME = "$joineeName";
	private static final String ASSIGNEE_NAME = "$assigneeName";
	@Autowired
	private ConfigurationRepository configurationRepository;
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Creates a json object containing information required to send an email to
	 * the assigned employee, including email content, to address, cc and
	 * subject.
	 * 
	 * @param employeeId
	 * @param joineeName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getAssigneeEmailData(String employeeId, String joineeName)
	{
		Employee assignee = employeeService.findEmployeeById(employeeId);
		Configuration assigneeTemplate = configurationRepository
				.findOneByConfigurationType(CONFIGURATION_TYPE.ASSIGNEE_EMAIL_TEMPLATE);
		Configuration assigneeSubject = configurationRepository
				.findOneByConfigurationType(CONFIGURATION_TYPE.ASSIGNEE_EMAIL_SUBJECT);
		Configuration assigneeCC = configurationRepository.findOneByConfigurationType(CONFIGURATION_TYPE.ASSIGNEE_EMAIL_CC);
		String cc = assigneeCC.getContent();
		String subject = assigneeSubject.getContent();
		String template = assigneeTemplate.getContent();
		// TODO remove this once db is populated with template information.
		template = "Hello " + ASSIGNEE_NAME + ", <br/>You have been assigned to be the buddy for " + JOINEE_NAME
				+ "<br/>Regards,<br/>HR";
		Map<String, String> placeholderName2Values = new HashMap<String, String>();
		placeholderName2Values.put(ASSIGNEE_NAME, assignee.getName());
		placeholderName2Values.put(JOINEE_NAME, joineeName);
		String emailContent = resolveTemplatePlaceholders(template, placeholderName2Values);
		String toAddress = generateToAddress(assignee);
		JSONObject emailData = new JSONObject();
		emailData.put(EMAIL_CONTENT, emailContent);
		emailData.put(TO_ADDRESS, toAddress);
		emailData.put(SUBJECT, subject);
		emailData.put("CC", cc);
		return emailData;
	}

	private String generateToAddress(Employee assignee)
	{
		String name = assignee.getName();
		String[] parsedName = name.split(" ");
		String toAddress = parsedName[0] + "." + parsedName[1] + "@talentica.com";
		return toAddress;
	}

	private String resolveTemplatePlaceholders(String template, Map<String, String> placeholderName2Values)
	{
		String result = template;
		for (Entry<String, String> key2value : placeholderName2Values.entrySet())
		{
			result = template.replace(key2value.getKey(), key2value.getValue());
		}
		return result;
	}
}