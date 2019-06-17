package com.felixsoinfotech.SpringbootElasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class })
@EnableJpaRepositories("com.felixsoinfotech.SpringbootElasticsearch.repository")
@EnableElasticsearchRepositories("com.felixsoinfotech.SpringbootElasticsearch.elasticRepository")
public class SpringbootElasticsearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootElasticsearchApplication.class, args);
	}
}
