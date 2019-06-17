package com.felixsoinfotech.SpringbootElasticsearch.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.felixsoinfotech.SpringbootElasticsearch.elasticRepository.EmployeeElasticRepository;
import com.felixsoinfotech.SpringbootElasticsearch.model.Employee;
import com.felixsoinfotech.SpringbootElasticsearch.repository.EmployeeRepository;
import com.felixsoinfotech.SpringbootElasticsearch.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	ElasticsearchOperations elasticsearchOperations;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeElasticRepository employeeElasticRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Iterable<Employee> findAllEmployeeElastic(Pageable pageable) {

		return employeeElasticRepository.findAll();

	}

	@Override
	public List<Employee> searchByName(String searchTerm) {
		// TODO Auto-generated method stub

		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.matchQuery("name", searchTerm).fuzziness(Fuzziness.TWO)).build();
		Page<Employee> emppage = employeeElasticRepository.search(searchQuery);
		System.out.println("*********************************" + emppage.getContent());
		return emppage.getContent();

	}

	@Override
	public Employee saveEmployeeElastic(Employee employee) {
		// TODO Auto-generated method stub
		return employeeElasticRepository.save(employee);
	}

	@Override
	public Employee findById(Long id) {
		// TODO Auto-generated method stub
		return employeeElasticRepository.findById(id).get();
	}

	@Override
	public List<Employee> searchAllEmployee() {
		// TODO Auto-generated method stub
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
		return employeeElasticRepository.search(searchQuery).getContent();
	}

	@Override
	public List<Employee> searchBySalary(Double salary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchEmployeeByNameAndSalary(String name, Double salary) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(
				QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", name).fuzziness(Fuzziness.TWO))
						.must(QueryBuilders.matchQuery("salary", salary)))
				.build();
		return employeeElasticRepository.search(searchQuery).getContent();
	}

	@Override
	public List<Employee> searchEmployeeByNameTermQuery(String name) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", name))).build();
		SearchQuery searchQuery1 = new NativeSearchQueryBuilder().withQuery(termQuery("name", name)).build();
		return employeeElasticRepository.search(searchQuery).getContent();
	}

	@Override
	public List<Employee> searchAndSortEmployees(Pageable pageable) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery())
				.withSort(SortBuilders.fieldSort("name").order(SortOrder.ASC)).withPageable(pageable).build();
		return employeeElasticRepository.search(searchQuery).getContent();
	}

	@Override
	public Employee saveEmployeeElasticWithAddress(Employee employee) {
		// TODO Auto-generated method stub
		return employeeElasticRepository.save(employee);
	}

}
