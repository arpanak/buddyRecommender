package com.recommender.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recommender.db.Employee;
import com.recommender.db.Joinee;
import com.recommender.frontend.Email;
import com.recommender.repositories.EmployeeRepository;

/**
 * This class handles assignment related features, including making and tracking
 * assignments.
 * 
 * @author AshwinV
 * 
 */
@Service
public class AssignmentService
{

	private static final Logger LOGGER = LoggerFactory.getLogger(AssignmentService.class);
	private static final int MAX_ASSIGNEES = 3;

	@Autowired
	private MailService mailService;
	@Autowired
	private EmployeeRepository employeeRepository;

	public enum ASSIGNMENT_STATUS
	{
		SUCCESS("Assignment successful."),
		BUDDY_CAPACITY_EXCEEDED("Assignment failed - Buddy assignment limit reached."),
		ERROR("Assignment failed.");

		private String status;

		ASSIGNMENT_STATUS(String status)
		{
			this.status = status;
		}

		public String getStatus()
		{
			return this.status;
		}
	}

	/**
	 * Check if employee is already assigned to max number of joinees If not,
	 * assign employee as buddy and send a mail to the employee.
	 * 
	 * @param buddy
	 * @param joinee
	 */
	public ASSIGNMENT_STATUS assignAndMailBuddy(Employee buddy, Joinee joinee, Email emailToSend)
	{
		ASSIGNMENT_STATUS result = null;
		List<Joinee> assignedJoinees = buddy.getAssignedJoinees();
		if (assignedJoinees.size() > MAX_ASSIGNEES - 1)
		{
			result = ASSIGNMENT_STATUS.BUDDY_CAPACITY_EXCEEDED;
		}
		else
		{
			try
			{
				buddy.getAssignedJoinees().add(joinee);
				employeeRepository.save(buddy);
				mailService.sendMail(emailToSend);
				result = ASSIGNMENT_STATUS.SUCCESS;
			}
			catch (Exception ex)
			{
				LOGGER.error("Error occurred while assigning buddy: {}", ex.getMessage());
				result = ASSIGNMENT_STATUS.ERROR;
			}
		}
		return result;
	}
}
