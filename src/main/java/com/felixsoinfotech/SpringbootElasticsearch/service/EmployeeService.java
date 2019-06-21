package com.felixsoinfotech.SpringbootElasticsearch.service;

/** 
 * @author muhammedruhail
*/
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.felixsoinfotech.SpringbootElasticsearch.model.Employee;

public interface EmployeeService {

	/**
	 * Save an employee.
	 *
	 * @param employee the entity to save
	 * @return the persisted entity
	 */
	Employee saveEmployee(Employee employee);

	/**
	 * Save an employee to elastic.
	 *
	 * @param employee the entity to save
	 * @return the persisted entity
	 */
	Employee saveEmployeeElastic(Employee employee);

	/**
	 * Get all the employees.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	List<Employee> findAllEmployee(Pageable pageable);

	/**
	 * Get all the employees.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	List<Employee> findAllEmployeeElastic(Pageable pageable);

	/**
	 * Get all the employees.
	 *
	 * @param searchTerm the term to search
	 * @return the list of entities
	 */
	List<Employee> searchByName(String searchTerm);

	/**
	 * Get all the employees.
	 *
	 * @param salary
	 * @return the list of entities
	 */
	List<Employee> searchBySalary(Double salary);

	/**
	 * Get employees.
	 *
	 * @param id employee id
	 * @return the employee entity
	 */
	Employee findById(Long id);

	/**
	 * Get all the employees.
	 *
	 * @return the list of entities
	 */
	List<Employee> searchAllEmployee();

	/**
	 * Get all the employees.
	 *
	 * @param name
	 * @param salary
	 * @return the list of entities
	 */
	List<Employee> searchEmployeeByNameAndSalary(String name, Double salary);

	/**
	 * Get all the employees.
	 *
	 * @param name
	 * @return the list of entities
	 */
	List<Employee> searchEmployeeByNameUsingTermQuery(String name);

	/**
	 * Get all the employees.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	List<Employee> searchAndSortEmployees(Pageable pageable);

	/**
	 * Save an employee to elastic.
	 *
	 * @param employee the entity to save
	 * @return the persisted entity
	 */
	Employee saveEmployeeElasticWithAddress(Employee employee);

}
