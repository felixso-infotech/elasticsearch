package com.felixsoinfotech.SpringbootElasticsearch.controller;
/** 
 * @author muhammedruhail
*/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felixsoinfotech.SpringbootElasticsearch.model.Employee;
import com.felixsoinfotech.SpringbootElasticsearch.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	@Autowired
	EmployeeService employeeService;

	/**
	 * POST /employee : Create a new employee.
	 *
	 * @param employee the employee to create
	 * @return the ResponseEntity with body the new employee
	 */
	@PostMapping("/employee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.saveEmployee(employee);
		return ResponseEntity.ok(emp);
	}

	/**
	 * GET /employees : get all the employees.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with employees in body
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee(Pageable pageable) {

		List<Employee> emp = employeeService.findAllEmployee(pageable);
		return ResponseEntity.ok(emp);
	}

	/**
	 * POST /employee-elastic : Create a new employee.
	 *
	 * @param employee the employee to create
	 * @return the ResponseEntity with body the new employee
	 */
	@PostMapping("/employee-elastic")
	public ResponseEntity<Employee> saveEmployeeElastic(@RequestBody Employee employee) {
		Employee emp = employeeService.saveEmployeeElastic(employee);
		return ResponseEntity.ok(emp);
	}

	/**
	 * GET /employees-elastic : get all the employees.
	 *
	 * @param pageable the pagination information
	 * @param pageable the pagination information
	 * @return the ResponseEntity with employees in body
	 */
	@GetMapping("/employees-elastic")
	public ResponseEntity<List<Employee>> getAllEmployeeElastic(Pageable pageable) {
		List<Employee> emp = employeeService.findAllEmployeeElastic(pageable);
		return ResponseEntity.ok(emp);
	}

	/**
	 * GET /employee-search-by-name/{name} : get an employee by name.
	 *
	 * @param name employee name
	 * @return the ResponseEntity with employees in body
	 */
	@GetMapping("/employee-search-by-name/{name}")
	public ResponseEntity<List<Employee>> searchEmployeeByName(@PathVariable String name) {
		return ResponseEntity.ok(employeeService.searchByName(name));
	}

	/**
	 * GET /employee-by-id/{id} : get an employee by id.
	 *
	 * @param id employee id
	 * @return the ResponseEntity with employees in body
	 */
	@GetMapping("/employee-by-id/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee emp = employeeService.findById(id);
		return ResponseEntity.ok(emp);
	}

	/**
	 * GET /employee-search-by-salary/{salary} : get employees by salary.
	 *
	 * @param salary employee salary
	 * @return the ResponseEntity with employees in body
	 */
	@GetMapping("/employee-search-by-salary/{salary}")
	public ResponseEntity<List<Employee>> searchEmployeeBySalary(@PathVariable Double salary) {
		List<Employee> emp = employeeService.searchBySalary(salary);
		return ResponseEntity.ok(emp);
	}

	/**
	 * GET /employees-search : get employees.
	 *
	 * @param salary   employee salary
	 * @param pageable the pagination information
	 * @return the ResponseEntity with employees in body
	 */
	@GetMapping("/employees-search")
	public ResponseEntity<List<Employee>> searchAllEmployees(Pageable pageable) {
		List<Employee> emp = employeeService.searchAllEmployee();
		return ResponseEntity.ok(emp);
	}

	/**
	 * GET /search-employees-by-name-salary/{name}/{salary} : get employees by name
	 * and salary.
	 *
	 * @param name   employee name
	 * @param salary employee salary
	 * @return the ResponseEntity with employees in body
	 */
	@GetMapping("/search-employees-by-name-salary/{name}/{salary}")
	public ResponseEntity<List<Employee>> searchEmployeesByNameAndSalary(@PathVariable String name,
			@PathVariable Double salary) {
		List<Employee> emp = employeeService.searchEmployeeByNameAndSalary(name, salary);
		return ResponseEntity.ok(emp);
	}

	/**
	 * GET /search-employees-by-name-term-query/{name} : get employees by name
	 *
	 * @param name employee name
	 * @return the ResponseEntity with employees in body
	 */
	@GetMapping("/search-employees-by-name-term-query/{name}")
	public ResponseEntity<List<Employee>> searchEmployeesByNameUsingTermQuery(@PathVariable String name) {
		List<Employee> emp = employeeService.searchEmployeeByNameUsingTermQuery(name);
		return ResponseEntity.ok(emp);
	}

	/**
	 * GET /search-sorted-employees : get employees
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with employees in body
	 */
	@GetMapping("/search-sorted-employees")
	public ResponseEntity<List<Employee>> searchAndSortEmployees(Pageable pageable) {
		List<Employee> emp = employeeService.searchAndSortEmployees(pageable);
		return ResponseEntity.ok(emp);
	}

}
