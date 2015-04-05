package com.recommender.db;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * Represents an employee.
 * 
 * @author AshwinV
 * 
 */
@Entity
@Data
@EqualsAndHashCode(exclude="id")
public class Employee
{
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String graduateDegree;
	private String graduateInstitute;
	private String graduateYear;
	private String employeeCode;
	private String currentTeam;
	private String careerLevel;
	private String emailAddress;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Joinee> assignedJoinees = new ArrayList<Joinee>();

}
