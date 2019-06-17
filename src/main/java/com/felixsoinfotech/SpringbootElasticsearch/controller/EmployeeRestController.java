package com.felixsoinfotech.SpringbootElasticsearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

	@PostMapping("/employee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@PostMapping("/employee-elastic")
	public Employee saveEmployeeElastic(@RequestBody Employee employee) {
		return employeeService.saveEmployeeElastic(employee);
	}

	@GetMapping("/employee")
	public List<Employee> getAllEmployeeElastic(Pageable pageable) {
		List<Employee> emp = new ArrayList<Employee>();
		employeeService.findAllEmployeeElastic(pageable).forEach(i -> emp.add(i));
		return emp;
	}

	@GetMapping("/employee/{name}")
	public List<Employee> searchEmployeeByName(@PathVariable String name) {
		return employeeService.searchByName(name);
	}

	@GetMapping("/employeeid/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return employeeService.findById(id);
	}

	@GetMapping("/employeesalary/{salary}")
	public List<Employee> searchEmployeeBySalary(@PathVariable Double salary) {
		return employeeService.searchBySalary(salary);
	}

	@GetMapping("/search-all-employees")
	public List<Employee> searchAllEmployees() {
		return employeeService.searchAllEmployee();
	}

	@GetMapping("/search-employees-by-name-salary/{name}/{salary}")
	public List<Employee> searchEmployeesByNameAndSalary(@PathVariable String name, @PathVariable Double salary) {
		return employeeService.searchEmployeeByNameAndSalary(name, salary);
	}

	@GetMapping("/search-employees-by-name-term-query/{name}")
	public List<Employee> searchEmployeesByNameUsingTermQuery(@PathVariable String name) {
		return employeeService.searchEmployeeByNameTermQuery(name);
	}

	@GetMapping("/search-sort-employees")
	public List<Employee> searchAndSortEmployees(Pageable pageable) {
		return employeeService.searchAndSortEmployees(pageable);
	}

}
