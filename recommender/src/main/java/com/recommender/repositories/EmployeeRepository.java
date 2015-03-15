package com.recommender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.recommender.db.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{

}
