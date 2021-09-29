package com.dept.java.demo.main.api;

import com.dept.java.demo.application.ValidationFailed;
import com.dept.java.demo.domain.Poll;
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

    private final CreateNew createNew;
    private final GetAll getAll;
    private final GetById getById;

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
        return getById.execute(pollId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
