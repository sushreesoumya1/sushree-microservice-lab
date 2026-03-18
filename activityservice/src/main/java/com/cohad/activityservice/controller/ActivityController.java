package com.cohad.activityservice.controller;

import com.cohad.activityservice.document.ActivityEvent;
import com.cohad.activityservice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/events")
//@RequiredArgsConstructor
public class ActivityController {

    private final ActivityRepository repo;

    public ActivityController(ActivityRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ActivityEvent create(@RequestBody ActivityEvent event) {
        return repo.save(
                new ActivityEvent(
                        null,
                        event.userId(),
                        event.eventType(),
                        Instant.now(),
                        event.metadata()
                )
        );
    }

    @GetMapping("/user/{userId}")
    public List<ActivityEvent> byUser(@PathVariable String userId) {
        return repo.findByUserId(userId);
    }

    @GetMapping("/type/{eventType}")
    public List<ActivityEvent> byType(@PathVariable String eventType) {
        return repo.findByEventType(eventType);
    }

    @GetMapping
    public List<ActivityEvent> getAllEvents(){
        return repo.findAll();
    }

    /*@GetMapping
    public Page<ActivityEvent> getAll(Pageable pageable) {
        return repo.findAll(pageable);
    }*/
}
