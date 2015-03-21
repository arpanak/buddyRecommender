package com.recommender.db;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 
 * Represents a new joinee
 * 
 * @author AshwinV
 * 
 */
@Entity
public class Joinee
{
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String place;
	private String college;
	@ElementCollection(targetClass=String.class)
	private List<String> previousOrganizations;
	@ElementCollection(targetClass=String.class)
	private List<String> skills;
	private int yearOfGraduation;
	private String team;

	public Joinee(String name, String place, String college, List<String> previousOrganizations, List<String> skills,
			int yearOfGraduation, String team)
	{
		super();
		this.name = name;
		this.place = place;
		this.college = college;
		this.previousOrganizations = previousOrganizations;
		this.skills = skills;
		this.yearOfGraduation = yearOfGraduation;
		this.team = team;
	}
	
	public Joinee()
	{
		super();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPlace()
	{
		return place;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

	public List<String> getPreviousOrganizations()
	{
		return previousOrganizations;
	}

	public void setPreviousOrganizations(List<String> previousOrganizations)
	{
		this.previousOrganizations = previousOrganizations;
	}

	public List<String> getSkills()
	{
		return skills;
	}

	public void setSkills(List<String> skills)
	{
		this.skills = skills;
	}

	public int getYearOfGraduation()
	{
		return yearOfGraduation;
	}

	public void setYearOfGraduation(int yearOfGraduation)
	{
		this.yearOfGraduation = yearOfGraduation;
	}

	public String getTeam()
	{
		return team;
	}

	public void setTeam(String team)
	{
		this.team = team;
	}

}