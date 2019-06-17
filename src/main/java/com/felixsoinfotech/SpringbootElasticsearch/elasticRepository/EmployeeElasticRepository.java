package com.felixsoinfotech.SpringbootElasticsearch.elasticRepository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.felixsoinfotech.SpringbootElasticsearch.model.Employee;

@Repository
public interface EmployeeElasticRepository extends ElasticsearchRepository<Employee, Long> {
	Optional<Employee> findByName(String name);
}
