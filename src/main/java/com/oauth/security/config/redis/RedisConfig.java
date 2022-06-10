package com.oauth.security.config.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import lombok.extern.slf4j.Slf4j;

/**
 * If the @EnableCaching annotation is present in your app, Spring Boot checks
 * dependencies available on your class path and configures an appropriate
 * CacheManager
 * 
 * ConcurrentHashMap is used by default and is probably sufficient for simple
 * use cases.
 * 
 * 
 * @Bean public CacheManager cacheManager() { return new
 *       org.springframework.cache.concurrent.ConcurrentMapCacheManager(); }
 * 
 * @author gaian
 *
 */

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "oauth.redis", name = "enable", havingValue = "true")
public class RedisConfig {

	@Autowired
	private RedisProperties redisProperties;

	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory() {
		log.info("----Redis bean creation...-----");
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisProperties.getRedisHost(),
				redisProperties.getRedisPort());
		return new LettuceConnectionFactory(config);
	}

//	@Bean
//	public RedisCacheConfiguration redisCacheConfiguration() {
//		log.info("----Redis Configuraiton-----");
//		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(60)).disableCachingNullValues()
//				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//	}

}
