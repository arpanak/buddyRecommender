package com.recommender.db;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * Represents an employee.
 * 
 * @author AshwinV
 * 
 */
@Entity
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

	public Employee()
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

	public List<Joinee> getAssignedJoinees()
	{
		return assignedJoinees;
	}

	public void setAssignedJoinees(List<Joinee> assignedJoinees)
	{
		this.assignedJoinees = assignedJoinees;
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

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignedJoinees == null) ? 0 : assignedJoinees.hashCode());
		result = prime * result + ((careerLevel == null) ? 0 : careerLevel.hashCode());
		result = prime * result + ((currentDesignation == null) ? 0 : currentDesignation.hashCode());
		result = prime * result + ((currentTeam == null) ? 0 : currentTeam.hashCode());
		result = prime * result + ((experienceCalculatedAsOf == null) ? 0 : experienceCalculatedAsOf.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((graduateDegree == null) ? 0 : graduateDegree.hashCode());
		result = prime * result + ((graduateInstitute == null) ? 0 : graduateInstitute.hashCode());
		result = prime * result + ((graduateStream == null) ? 0 : graduateStream.hashCode());
		result = prime * result + ((graduateYear == null) ? 0 : graduateYear.hashCode());
		result = prime * result + ((hr == null) ? 0 : hr.hashCode());
		result = prime * result + ((jobFunction == null) ? 0 : jobFunction.hashCode());
		result = prime * result + ((joiningDate == null) ? 0 : joiningDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((postGraduateDegree == null) ? 0 : postGraduateDegree.hashCode());
		result = prime * result + ((postGraduateInstitute == null) ? 0 : postGraduateInstitute.hashCode());
		result = prime * result + ((postGraduateStream == null) ? 0 : postGraduateStream.hashCode());
		result = prime * result + ((postGraduateYear == null) ? 0 : postGraduateYear.hashCode());
		result = prime * result + ((reportingManager == null) ? 0 : reportingManager.hashCode());
		result = prime * result + ((seniorManager == null) ? 0 : seniorManager.hashCode());
		result = prime * result + ((workingWithUsSince == null) ? 0 : workingWithUsSince.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Employee other = (Employee) obj;
		if (assignedJoinees == null)
		{
			if (other.assignedJoinees != null)
			{
				return false;
			}
		}
		else if (!assignedJoinees.equals(other.assignedJoinees))
		{
			return false;
		}
		if (careerLevel == null)
		{
			if (other.careerLevel != null)
			{
				return false;
			}
		}
		else if (!careerLevel.equals(other.careerLevel))
		{
			return false;
		}
		if (currentDesignation == null)
		{
			if (other.currentDesignation != null)
			{
				return false;
			}
		}
		else if (!currentDesignation.equals(other.currentDesignation))
		{
			return false;
		}
		if (currentTeam == null)
		{
			if (other.currentTeam != null)
			{
				return false;
			}
		}
		else if (!currentTeam.equals(other.currentTeam))
		{
			return false;
		}
		if (experienceCalculatedAsOf == null)
		{
			if (other.experienceCalculatedAsOf != null)
			{
				return false;
			}
		}
		else if (!experienceCalculatedAsOf.equals(other.experienceCalculatedAsOf))
		{
			return false;
		}
		if (gender == null)
		{
			if (other.gender != null)
			{
				return false;
			}
		}
		else if (!gender.equals(other.gender))
		{
			return false;
		}
		if (graduateDegree == null)
		{
			if (other.graduateDegree != null)
			{
				return false;
			}
		}
		else if (!graduateDegree.equals(other.graduateDegree))
		{
			return false;
		}
		if (graduateInstitute == null)
		{
			if (other.graduateInstitute != null)
			{
				return false;
			}
		}
		else if (!graduateInstitute.equals(other.graduateInstitute))
		{
			return false;
		}
		if (graduateStream == null)
		{
			if (other.graduateStream != null)
			{
				return false;
			}
		}
		else if (!graduateStream.equals(other.graduateStream))
		{
			return false;
		}
		if (graduateYear == null)
		{
			if (other.graduateYear != null)
			{
				return false;
			}
		}
		else if (!graduateYear.equals(other.graduateYear))
		{
			return false;
		}
		if (hr == null)
		{
			if (other.hr != null)
			{
				return false;
			}
		}
		else if (!hr.equals(other.hr))
		{
			return false;
		}
		if (jobFunction == null)
		{
			if (other.jobFunction != null)
			{
				return false;
			}
		}
		else if (!jobFunction.equals(other.jobFunction))
		{
			return false;
		}
		if (joiningDate == null)
		{
			if (other.joiningDate != null)
			{
				return false;
			}
		}
		else if (!joiningDate.equals(other.joiningDate))
		{
			return false;
		}
		if (name == null)
		{
			if (other.name != null)
			{
				return false;
			}
		}
		else if (!name.equals(other.name))
		{
			return false;
		}
		if (postGraduateDegree == null)
		{
			if (other.postGraduateDegree != null)
			{
				return false;
			}
		}
		else if (!postGraduateDegree.equals(other.postGraduateDegree))
		{
			return false;
		}
		if (postGraduateInstitute == null)
		{
			if (other.postGraduateInstitute != null)
			{
				return false;
			}
		}
		else if (!postGraduateInstitute.equals(other.postGraduateInstitute))
		{
			return false;
		}
		if (postGraduateStream == null)
		{
			if (other.postGraduateStream != null)
			{
				return false;
			}
		}
		else if (!postGraduateStream.equals(other.postGraduateStream))
		{
			return false;
		}
		if (postGraduateYear == null)
		{
			if (other.postGraduateYear != null)
			{
				return false;
			}
		}
		else if (!postGraduateYear.equals(other.postGraduateYear))
		{
			return false;
		}
		if (reportingManager == null)
		{
			if (other.reportingManager != null)
			{
				return false;
			}
		}
		else if (!reportingManager.equals(other.reportingManager))
		{
			return false;
		}
		if (seniorManager == null)
		{
			if (other.seniorManager != null)
			{
				return false;
			}
		}
		else if (!seniorManager.equals(other.seniorManager))
		{
			return false;
		}
		if (workingWithUsSince == null)
		{
			if (other.workingWithUsSince != null)
			{
				return false;
			}
		}
		else if (!workingWithUsSince.equals(other.workingWithUsSince))
		{
			return false;
		}
		return true;
	}

	
}
