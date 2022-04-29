package com.oauth.security.config.kakfa.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableKafka
@ConditionalOnProperty(prefix = "oauth.kafka", name = "enable", havingValue = "true")
public class OauthConsumerConfig {

	@Autowired
	private OauthConsumerProperties kafkaProperties;

	@Bean
	public Map<String, Object> consumerConfig() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getAutoOffsetReset());
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getKeyDeserializer());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getValueDeserializer());
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaProperties.getEnableAutoCommit());
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaProperties.getAutoCommitInterval());
		properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaProperties.getSessionTimeout());
		return properties;
	}

	@Bean
	public ConsumerFactory<String, String> kafkaConsumerFactory() {
		return new DefaultKafkaConsumerFactory<String, String>(consumerConfig());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(kafkaConsumerFactory());
		factory.setConcurrency(kafkaProperties.getConcurrency());
		factory.getContainerProperties().setPollTimeout(kafkaProperties.getTimeout());
		factory.getContainerProperties().setConsumerTaskExecutor(executor());
		return factory;
	}

	@Bean
	public AsyncListenableTaskExecutor executor() {
		ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
		tpte.setCorePoolSize(kafkaProperties.getPoolSize());
		return tpte;
	}

	@Bean("oauthConsumer")
	public OauthConsumer oauthConsumer() {
		return new OauthConsumer();
	}

}
