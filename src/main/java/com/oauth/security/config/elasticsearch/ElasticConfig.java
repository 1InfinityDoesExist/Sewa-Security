package com.oauth.security.config.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "oauth.elastic", name = "enable", havingValue = "true")
public class ElasticConfig {

	@Autowired
	private ElasticProperties elasticProperties;

	@Bean
	public RestHighLevelClient client() {
		log.info("-----Creating ElasticSearch Client Bean.-----");
		log.info("----Host : {} and port :{}", elasticProperties.getElasticHost(), elasticProperties.getElasticPort());
		RestHighLevelClient restClient = new RestHighLevelClient(RestClient.builder(new HttpHost(
				elasticProperties.getElasticHost(), elasticProperties.getElasticPort(), HttpHost.DEFAULT_SCHEME_NAME)));
		return restClient;
	}

}
