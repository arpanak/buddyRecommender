package com.recommender.db;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Represents a new joinee
 * 
 * @author AshwinV
 * 
 */
@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Joinee
{
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String college;
	private int yearOfGraduation;
	private String team;
	private Date buddyAssignedOn;
	private String careerLevel;
}