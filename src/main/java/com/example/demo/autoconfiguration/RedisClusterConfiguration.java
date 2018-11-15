package com.example.demo.autoconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

//@Configuration
//@EnableConfigurationProperties(RedisProperties.class)
public class RedisClusterConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        org.springframework.data.redis.connection.RedisClusterConfiguration clusterConfig = new org.springframework.data.redis.connection.RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        clusterConfig.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());

        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.set

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(clusterConfig, poolConfig);
        return new JedisConnectionFactory(
                new org.springframework.data.redis.connection.RedisClusterConfiguration(redisProperties.getCluster().getNodes())
        );
    }

}
