package com.oauth.security.config.kakfa.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OauthProducer {

	public OauthProducer() {
		log.info("-----OauthProducer bean created-----");
	}

	@Qualifier("oauthKafkaTemplate")
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String topic, String payload) {
		log.info("-----Topic  : {} and payload : {}", topic, payload);

		ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, payload);
		send.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub

			}

		});
	}
}
