package com.cohad.activityservice.controller;

import com.cohad.activityservice.document.ActivityEvent;
import com.cohad.activityservice.repository.ActivityRepository;
import com.cohad.activityservice.responseDTO.ActivityResponseDTO;
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
    public Page<ActivityResponseDTO> byUser(@PathVariable String userId, Pageable pageable) {
        return repo.findByUserId(userId, pageable)
                .map(event -> new ActivityResponseDTO(
                        event.id(),
                        event.userId(),
                        event.eventType(),
                        event.timestamp(),
                        event.metadata()
                ));
    }

    @GetMapping("/type/{eventType}")
    public Page<ActivityResponseDTO> byType(@PathVariable String eventType, Pageable pageable) {
        return repo.findByEventType(eventType, pageable)
                .map(event -> new ActivityResponseDTO(
                        event.id(),
                        event.userId(),
                        event.eventType(),
                        event.timestamp(),
                        event.metadata()
                ));
    }

    /*@GetMapping
    public List<ActivityEvent> getAllEvents(){
        return repo.findAll();
    }*/

    @GetMapping
    public Page<ActivityResponseDTO> getAll(Pageable pageable) {
        return repo.findAll(pageable)
                .map(event -> new ActivityResponseDTO(
                        event.id(),
                        event.userId(),
                        event.eventType(),
                        event.timestamp(),
                        event.metadata()
                ));
    }
}
