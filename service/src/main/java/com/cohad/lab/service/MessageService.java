package com.cohad.lab.service;

import com.cohad.lab.cache.MessageCache;
import com.cohad.lab.entity.Message;
import com.cohad.lab.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageCache messageCache;

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    public MessageService(MessageRepository messageRepository, MessageCache messageCache) {
        this.messageRepository = messageRepository;
        this.messageCache = messageCache;
    }

    /**
     * Cache-aside read:
     * 1) Try Redis
     * 2) If miss -> DB
     * 3) Put into Redis (TTL)
     */
    @Transactional(readOnly = true)
    public Message getById(Long id) {
        var cached = messageCache.get(id);

        if (cached.isPresent()) {
            log.info("CACHE_HIT id={}", id);
            return cached.get();
        }

        log.info("CACHE_MISS id={}", id);

        Message fromDb = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Message not found: " + id));

        messageCache.put(fromDb);
        return fromDb;
    }

    @Transactional(readOnly = true)
    public List<Message> getAll() {
        // (V15 focus is GET-by-id caching; list caching comes later)
        return messageRepository.findAll();
    }

    @Transactional
    public Message create(Message message) {
        // Ensure it's treated as new
        message.setId(null);

        Message saved = messageRepository.save(message);

        // Keep cache warm
        messageCache.put(saved);

        return saved;
    }

    @Transactional
    public Message update(Long id, Message incoming) {
        Message existing = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Message not found: " + id));

        // Update only what you allow (adjust fields based on your entity)
        existing.setContent(incoming.getContent());

        Message saved = messageRepository.save(existing);

        // Refresh cache with latest value
        messageCache.put(saved);

        return saved;
    }

    @Transactional
    public void delete(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new IllegalArgumentException("Message not found: " + id);
        }

        messageRepository.deleteById(id);

        // Evict cache entry
        messageCache.evict(id);
    }
}
