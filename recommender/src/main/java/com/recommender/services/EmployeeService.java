package com.recommender.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recommender.db.Employee;
import com.recommender.repositories.EmployeeRepository;

/**
 * Contains logic to retrieve data from csv and return object representations of
 * data.
 * 
 * @author AshwinV
 */
@Service
public class EmployeeService
{

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}

	public Employee findEmployeeById(String id)
	{
		return employeeRepository.findOne(Integer.parseInt(id));
	}

	/**
	 * Get list of buddies
	 * 
	 * @return the list of employees who have been assigned to at least one
	 *         joinee
	 */
	public List<Employee> getAllAssignedEmployees()
	{
		List<Employee> allEmployees = employeeRepository.findAll();
		List<Employee> assignedEmployees = new ArrayList<Employee>();
		for (Employee employee : allEmployees)
		{
			if (CollectionUtils.isNotEmpty(employee.getAssignedJoinees()))
			{
				assignedEmployees.add(employee);
			}
		}
		return assignedEmployees;
	}
}