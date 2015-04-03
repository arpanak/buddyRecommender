package com.recommender.db;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

/**
 * 
 * Represents an employee.
 * 
 * @author AshwinV
 * 
 */
@Entity
@Data 
public class Employee
{
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String gender;
	private String graduateDegree;
	private String graduateStream;
	private String graduateInstitute;
	private String graduateYear;
	private String postGraduateDegree;
	private String postGraduateStream;
	private String postGraduateInstitute;
	private String postGraduateYear;
	private String jobFunction;
	private String currentTeam;
	private String hr;
	private String reportingManager;
	private String seniorManager;
	private String joiningDate;
	private String experienceCalculatedAsOf;
	private String workingWithUsSince;
	private String careerLevel;
	private String currentDesignation;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Joinee> assignedJoinees = new ArrayList<Joinee>();
	
}
