package com.recommender.db;


/**
 * 
 * Represents an employee.
 * 
 * @author AshwinV
 * 
 */
public class Employee
{
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
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getGraduateDegree()
	{
		return graduateDegree;
	}
	public void setGraduateDegree(String graduateDegree)
	{
		this.graduateDegree = graduateDegree;
	}
	public String getGraduateStream()
	{
		return graduateStream;
	}
	public void setGraduateStream(String graduateStream)
	{
		this.graduateStream = graduateStream;
	}
	public String getGraduateInstitute()
	{
		return graduateInstitute;
	}
	public void setGraduateInstitute(String graduateInstitute)
	{
		this.graduateInstitute = graduateInstitute;
	}
	public String getGraduateYear()
	{
		return graduateYear;
	}
	public void setGraduateYear(String graduateYear)
	{
		this.graduateYear = graduateYear;
	}
	public String getPostGraduateDegree()
	{
		return postGraduateDegree;
	}
	public void setPostGraduateDegree(String postGraduateDegree)
	{
		this.postGraduateDegree = postGraduateDegree;
	}
	public String getPostGraduateStream()
	{
		return postGraduateStream;
	}
	public void setPostGraduateStream(String postGraduateStream)
	{
		this.postGraduateStream = postGraduateStream;
	}
	public String getPostGraduateInstitute()
	{
		return postGraduateInstitute;
	}
	public void setPostGraduateInstitute(String postGraduateInstitute)
	{
		this.postGraduateInstitute = postGraduateInstitute;
	}
	public String getPostGraduateYear()
	{
		return postGraduateYear;
	}
	public void setPostGraduateYear(String postGraduateYear)
	{
		this.postGraduateYear = postGraduateYear;
	}
	public String getJobFunction()
	{
		return jobFunction;
	}
	public void setJobFunction(String jobFunction)
	{
		this.jobFunction = jobFunction;
	}
	public String getCurrentTeam()
	{
		return currentTeam;
	}
	public void setCurrentTeam(String currentTeam)
	{
		this.currentTeam = currentTeam;
	}
	public String getHr()
	{
		return hr;
	}
	public void setHr(String hr)
	{
		this.hr = hr;
	}
	public String getReportingManager()
	{
		return reportingManager;
	}
	public void setReportingManager(String reportingManager)
	{
		this.reportingManager = reportingManager;
	}
	public String getSeniorManager()
	{
		return seniorManager;
	}
	public void setSeniorManager(String seniorManager)
	{
		this.seniorManager = seniorManager;
	}
	public String getJoiningDate()
	{
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate)
	{
		this.joiningDate = joiningDate;
	}
	public String getExperience()
	{
		return experienceCalculatedAsOf;
	}
	public void setExperience(String experience)
	{
		this.experienceCalculatedAsOf = experience;
	}
	public String getWorkingWithUsSince()
	{
		return workingWithUsSince;
	}
	public void setWorkingWithUsSince(String workingWithUsSince)
	{
		this.workingWithUsSince = workingWithUsSince;
	}
	public String getCareerLevel()
	{
		return careerLevel;
	}
	public void setCareerLevel(String careerLevel)
	{
		this.careerLevel = careerLevel;
	}
	public String getCurrentDesignation()
	{
		return currentDesignation;
	}
	public void setCurrentDesignation(String currentDesignation)
	{
		this.currentDesignation = currentDesignation;
	}
	
	@Override
	public String toString()
	{
		return "Employee [name=" + name + ", gender=" + gender + ", graduateDegree=" + graduateDegree + ", graduateStream="
				+ graduateStream + ", graduateInstitute=" + graduateInstitute + ", graduateYear=" + graduateYear
				+ ", postGraduateDegree=" + postGraduateDegree + ", postGraduateStream=" + postGraduateStream
				+ ", postGraduateInstitute=" + postGraduateInstitute + ", postGraduateYear=" + postGraduateYear
				+ ", jobFunction=" + jobFunction + ", currentTeam=" + currentTeam + ", hr=" + hr + ", reportingManager="
				+ reportingManager + ", seniorManager=" + seniorManager + ", joiningDate=" + joiningDate + ", experience="
				+ experienceCalculatedAsOf + ", workingWithUsSince=" + workingWithUsSince + ", careerLevel=" + careerLevel
				+ ", currentDesignation=" + currentDesignation + "]";
	}
	
}
