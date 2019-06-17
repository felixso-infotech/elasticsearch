package com.felixsoinfotech.SpringbootElasticsearch.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.felixsoinfotech.SpringbootElasticsearch.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

	Employee saveEmployeeElastic(Employee employee);

	Iterable<Employee> findAllEmployeeElastic(Pageable pageable);

	List<Employee> searchByName(String searchTerm);

	List<Employee> searchBySalary(Double salary);

	Employee findById(Long id);

	List<Employee> searchAllEmployee();

	List<Employee> searchEmployeeByNameAndSalary(String name, Double salary);

	List<Employee> searchEmployeeByNameTermQuery(String name);

	List<Employee> searchAndSortEmployees(Pageable pageable);

	Employee saveEmployeeElasticWithAddress(Employee employee);

}
