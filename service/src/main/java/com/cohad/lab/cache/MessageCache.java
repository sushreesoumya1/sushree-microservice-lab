package com.cohad.lab.cache;

import com.cohad.lab.entity.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class MessageCache {

    private static final Duration TTL = Duration.ofMinutes(5);
    private static final String PREFIX = "message:";

    private final RedisTemplate<String, Message> redisTemplate;

    public MessageCache(
            @Qualifier("messageRedisTemplate")
            RedisTemplate<String, Message> redisTemplate
    ) {
        this.redisTemplate = redisTemplate;
    }

    private String key(Long id) {
        return PREFIX + id;
    }

    public Optional<Message> get(Long id) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key(id)));
    }

    public void put(Message message) {
        if (message.getId() == null) return;
        redisTemplate.opsForValue().set(key(message.getId()), message, TTL);
    }

    public void evict(Long id) {
        redisTemplate.delete(key(id));
    }
}
