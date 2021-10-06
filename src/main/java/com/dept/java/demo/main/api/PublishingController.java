package com.dept.java.demo.main.api;

import com.dept.java.demo.application.publishing.PublishRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;
import com.dept.java.demo.application.publishing.PollPublisher;

@RestController
@RequestMapping("polls/{pollId}/publish")
@RequiredArgsConstructor
public class PublishingController {

    private final PollPublisher publisher;

    @PostMapping
    public ResponseEntity<Void> publish(@PathVariable("pollId") UUID pollId, @RequestBody PublishRequest publishRequest) {
        publisher.publish(pollId, publishRequest);
        return ResponseEntity.noContent().build();
    }
}
