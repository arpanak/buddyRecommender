package com.recommender.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Contains logic to retrieve data from csv and return object representations of
 * data.
 * 
 * @author AshwinV
 */
public class PersistenceManager
{

	//private static final String EXISTING_EMPLOYEES_CSV_FILE = "d:/existingEmployees.csv";
	private static final String EXISTING_EMPLOYEES_CSV_FILE = "/Users/ashwinvinod/Documents/workspace/buddyRecommender/existingEmployees.csv";

	/**
	 * Get list of Employee instances corresponding to existing employees.
	 * 
	 * @return the all employees
	 */
	public static List<Employee> getAllEmployees()
	{
		final String[] columns = new String[] { "name", "gender", "graduateDegree", "graduateStream", "graduateInstitute",
				"graduateYear", "postGraduateDegree", "postGraduateStream", "postGraduateInstitute", "postGraduateYear",
				"jobFunction", "currentTeam", "hr", "reportingManager", "seniorManager", "joiningDate",
				"experienceCalculatedAsOf", "workingWithUsSince", "careerLevel", "currentDesignation" };

		CsvBeanMapper<Employee, Employee> mapper = new CsvBeanMapper<Employee, Employee>();
		String csvFile;
		try
		{
			csvFile = getCsvFileAsString();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
		List<Employee> result = mapper.convertCsvToBeanEntries(csvFile, columns, Employee.class, Employee.class,
				getCsvEntryToEntityConverter());
		return result;
	}

	private static String getCsvFileAsString() throws Exception
	{
		File csvFile = new File(EXISTING_EMPLOYEES_CSV_FILE);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
		return IOUtils.toString(bufferedReader);
	}

	private static BeanUtilsBean getCsvEntryToEntityConverter()
	{
		BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean()
		{
			@SuppressWarnings("unchecked")
			@Override
			public Object convert(String value, @SuppressWarnings("rawtypes") Class clazz)
			{
				Object result = null;
				try
				{
					if (clazz.isEnum())
					{
						if (StringUtils.isNotBlank(value))
						{
							result = Enum.valueOf(clazz, value);
						}
					}
					else
					{
						result = super.convert(value, clazz);
					}
				}
				catch (Exception ex)
				{
					throw new RuntimeException(ex.getMessage());
				}
				return result;
			}
		});
		return beanUtilsBean;
	}
}