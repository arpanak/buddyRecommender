package com.recommender.services;

import java.util.List;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recommender.db.Employee;
import com.recommender.repositories.EmployeeRepository;
import com.recommender.utilities.CsvBeanMapper;

/**
 * This class contains functionality to import data from external sources.
 * 
 * @author ashwinvinod
 *
 */
@Service
public class DataImportService
{

	private static final Logger LOGGER = LoggerFactory.getLogger(DataImportService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	private String[] csvColumns = new String[] { "name", "gender", "graduateDegree", "graduateStream", "graduateInstitute",
			"graduateYear", "postGraduateDegree", "postGraduateStream", "postGraduateInstitute", "postGraduateYear",
			"jobFunction", "currentTeam", "hr", "reportingManager", "seniorManager", "joiningDate",
			"experienceCalculatedAsOf", "workingWithUsSince", "careerLevel", "currentDesignation" };

	public void importEmployeeDetails(String csvFileContents)
	{
		List<Employee> employees = getEmployeesFromCsv(csvFileContents);
		List<Employee> existingEmployees = employeeRepository.findAll();
		for (Employee employeeToSave : employees)
		{
			if (!existingEmployees.contains(employeeToSave))
			{
				employeeRepository.save(employeeToSave);
			}
		}
		LOGGER.info("Imported employee records: {} entries saved ", employees.size());
	}

	/**
	 * Get list of Employee instances corresponding to existing employees.
	 * 
	 * @return the all employees
	 */
	private List<Employee> getEmployeesFromCsv(String csvFileContents)
	{
		CsvBeanMapper<Employee, Employee> mapper = new CsvBeanMapper<Employee, Employee>();
		List<Employee> result = mapper.convertCsvToBeanEntries(csvFileContents, csvColumns, Employee.class, Employee.class,
				getCsvEntryToEntityConverter());
		return result;
	}

	private BeanUtilsBean getCsvEntryToEntityConverter()
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
