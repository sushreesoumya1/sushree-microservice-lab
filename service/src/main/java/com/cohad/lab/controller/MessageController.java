package com.cohad.lab.controller;

import com.cohad.lab.entity.Message;
import com.cohad.lab.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageRepository repository;

    @PostMapping
    public Message create(@RequestBody Message message) {
        return repository.save(message);
    }

    @GetMapping
    public List<Message> getAll() {
        return repository.findAll();
    }
}
