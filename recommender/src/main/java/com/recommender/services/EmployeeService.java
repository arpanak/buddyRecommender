package com.recommender.services;

import java.util.List;
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
}