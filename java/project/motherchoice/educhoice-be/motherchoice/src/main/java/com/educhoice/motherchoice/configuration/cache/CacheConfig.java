//package com.educhoice.motherchoice.configuration.cache;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.hash.Jackson2HashMapper;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@EnableCaching
//public class CacheConfig extends CachingConfigurerSupport{
//
//    @Value("${redis.server.hostname}")
//    private String redisHostname;
//
//    @Value("${redis.server.port}")
//    private int port;
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//
//        factory.setHostName(redisHostname);
//        factory.setPort(port);
//        return factory;
//    }
//
//    @Bean
//    public Jackson2HashMapper jackson2HashMapper() {
//        return new Jackson2HashMapper(new ObjectMapper(), true);
//    }
//}
