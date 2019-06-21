package com.felixsoinfotech.SpringbootElasticsearch.elasticRepository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.felixsoinfotech.SpringbootElasticsearch.model.Address;

public interface AddressElasticRepository extends ElasticsearchRepository<Address, Long> {

}
