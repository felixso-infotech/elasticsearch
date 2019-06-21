package com.felixsoinfotech.SpringbootElasticsearch.service.impl;

/** 
 * @author muhammedruhail
*/
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.felixsoinfotech.SpringbootElasticsearch.elasticRepository.AddressElasticRepository;
import com.felixsoinfotech.SpringbootElasticsearch.elasticRepository.EmployeeElasticRepository;
import com.felixsoinfotech.SpringbootElasticsearch.model.Address;
import com.felixsoinfotech.SpringbootElasticsearch.model.Employee;
import com.felixsoinfotech.SpringbootElasticsearch.repository.AddressRepository;
import com.felixsoinfotech.SpringbootElasticsearch.repository.EmployeeRepository;
import com.felixsoinfotech.SpringbootElasticsearch.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	EmployeeElasticRepository employeeElasticRepository;

	@Autowired
	AddressElasticRepository addressElasticRepository;

	/**
	 * Save an employee.
	 *
	 * @param employee the entity to save
	 * @return the persisted entity
	 */
	@Override
	public Employee saveEmployee(Employee employee) {
		Address address = addressRepository.save(employee.getAddress());
		employee.setAddress(address);
		return employeeRepository.save(employee);
	}

	/**
	 * Get all the employees.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	public List<Employee> findAllEmployee(Pageable pageable) {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	/**
	 * Get all the employees.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	public List<Employee> findAllEmployeeElastic(Pageable pageable) {

		return employeeElasticRepository.findAll(pageable).getContent();

	}

	/**
	 * Get all the employees.
	 *
	 * @param searchTerm the term to search
	 * @return the list of entities
	 */
	@Override
	public List<Employee> searchByName(String searchTerm) {
		// TODO Auto-generated method stub

		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.matchQuery("name", searchTerm).fuzziness(Fuzziness.TWO)).build();
		Page<Employee> emppage = employeeElasticRepository.search(searchQuery);
		return emppage.getContent();
	}

	/**
	 * Save an employee to elastic.
	 *
	 * @param employee the entity to save
	 * @return the persisted entity
	 */
	@Override
	public Employee saveEmployeeElastic(Employee employee) {
		// TODO Auto-generated method stub
		Address address = addressElasticRepository.save(employee.getAddress());
		employee.setAddress(address);
		return employeeElasticRepository.save(employee);
	}

	/**
	 * Get employees.
	 *
	 * @param id employee id
	 * @return the employee entity
	 */
	@Override
	public Employee findById(Long id) {
		// TODO Auto-generated method stub
		return employeeElasticRepository.findById(id).get();
	}

	/**
	 * Get all the employees.
	 *
	 * @return the list of entities
	 */
	@Override
	public List<Employee> searchAllEmployee() {
		// TODO Auto-generated method stub
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
		return employeeElasticRepository.search(searchQuery).getContent();
	}

	/**
	 * Get all the employees.
	 *
	 * @param salary
	 * @return the list of entities
	 */
	@Override
	public List<Employee> searchBySalary(Double salary) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get all the employees.
	 *
	 * @param name
	 * @param salary
	 * @return the list of entities
	 */
	@Override
	public List<Employee> searchEmployeeByNameAndSalary(String name, Double salary) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(
				QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", name).fuzziness(Fuzziness.TWO))
						.must(QueryBuilders.matchQuery("salary", salary)))
				.build();
		return employeeElasticRepository.search(searchQuery).getContent();
	}

	/**
	 * Get all the employees.
	 *
	 * @param name
	 * @return the list of entities
	 */
	@Override
	public List<Employee> searchEmployeeByNameUsingTermQuery(String name) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", name))).build();
		return employeeElasticRepository.search(searchQuery).getContent();
	}

	/**
	 * Get all the employees.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	public List<Employee> searchAndSortEmployees(Pageable pageable) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery())
				.withSort(SortBuilders.fieldSort("name").order(SortOrder.ASC)).withPageable(pageable).build();
		return employeeElasticRepository.search(searchQuery).getContent();
	}

	/**
	 * Save an employee to elastic.
	 *
	 * @param employee the entity to save
	 * @return the persisted entity
	 */
	@Override
	public Employee saveEmployeeElasticWithAddress(Employee employee) {
		// TODO Auto-generated method stub
		return employeeElasticRepository.save(employee);
	}

}
