package com.recommender.core;

import java.util.List;

/**
 * 
 * Represents a new joinee
 * 
 * @author AshwinV
 * 
 */
public class Joinee
{

	private String name;
	private String place;
	private String college;
	private List<String> previousOrganizations;
	
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
	
}