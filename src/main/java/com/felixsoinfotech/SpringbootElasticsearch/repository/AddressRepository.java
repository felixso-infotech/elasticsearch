package com.felixsoinfotech.SpringbootElasticsearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felixsoinfotech.SpringbootElasticsearch.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
