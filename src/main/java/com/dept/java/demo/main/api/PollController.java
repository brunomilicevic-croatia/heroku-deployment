package com.dept.java.demo.main.api;

import com.dept.java.demo.application.ValidationFailed;
import com.dept.java.demo.application.polls.CreatePoll;
import com.dept.java.demo.domain.polls.Poll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dept.java.demo.application.polls.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("polls")
@RequiredArgsConstructor
public class PollController {

    private final CreatePoll createNew;
    private final GetAllPolls getAll;
    private final GetPollById getPollById;

    @PostMapping
    public ResponseEntity<Poll> createNewPoll(@Valid @RequestBody Poll newPoll) throws ValidationFailed {
        createNew.execute(newPoll);
        return ResponseEntity.ok(newPoll);
    }

    @GetMapping
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        return ResponseEntity.ok(getAll.execute(null));
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<Poll> getPollById(@PathVariable("pollId") UUID pollId) {
        return getPollById.execute(pollId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
