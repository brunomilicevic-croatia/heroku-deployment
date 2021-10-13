package com.dept.java.demo.web.api;

import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import com.dept.java.demo.application.usecases.polls.CreatePollCommand;
import com.dept.java.demo.application.usecases.polls.QueryAllPolls;
import com.dept.java.demo.application.usecases.polls.QueryPollById;
import com.dept.java.demo.domain.Poll;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("polls")
@RequiredArgsConstructor
public class PollController {

    private final CreatePollCommand createPollCommand;
    private final QueryAllPolls queryAllPolls;
    private final QueryPollById queryPollById;

    @PostMapping
    public ResponseEntity<Poll> createNewPoll(@RequestBody Poll newPoll) throws ValidationFailed {
        createPollCommand.execute(newPoll);
        return ResponseEntity.ok(newPoll);
    }

    @GetMapping
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        return ResponseEntity.ok(queryAllPolls.execute(null));
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<Poll> getPollById(@PathVariable("pollId") UUID pollId) {
        return queryPollById.execute(pollId)
                            .map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
