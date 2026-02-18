package com.cohad.lab.config;

import com.cohad.lab.entity.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean(name = "messageRedisTemplate")
    public RedisTemplate<String, Message> messageRedisTemplate(
            RedisConnectionFactory connectionFactory
    ) {
        RedisTemplate<String, Message> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Key serializer (human readable keys)
        StringRedisSerializer keySerializer = new StringRedisSerializer();

        // Value serializer (store Message as JSON)
        Jackson2JsonRedisSerializer<Message> valueSerializer =
                new Jackson2JsonRedisSerializer<>(Message.class);

        template.setKeySerializer(keySerializer);
        template.setValueSerializer(valueSerializer);
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(valueSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
