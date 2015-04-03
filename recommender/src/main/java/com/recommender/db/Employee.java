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
	public enum CAREER_LEVEL
	{
		I1(10),
		I2(20),
		I3(30),
		M1(40),
		M2(50),
		M3(60),
		A1(70),
		A2(80),
		A3(90);

		private int value;

		private CAREER_LEVEL(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return this.value;
		}
	}

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
