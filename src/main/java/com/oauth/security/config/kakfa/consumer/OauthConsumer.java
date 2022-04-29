package com.oauth.security.config.kakfa.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OauthConsumer {

	public OauthConsumer() {
		log.info("-----OauthConsumer bean created.----");
	}

	@KafkaListener(topics = "${oauth.kafka.topic:payment}", groupId = "${oauth.kafka.groupId:payee}", containerFactory = "concurrentKafkaListenerContainerFactory")
	public void consume(@Payload String msg, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
			@Header(KafkaHeaders.OFFSET) int offset, Acknowledgment acknowledgment) {

		log.info("----Msg : {},   partition : {}, offset : {}", msg, partition, offset);

		acknowledgment.acknowledge();

	}

}
