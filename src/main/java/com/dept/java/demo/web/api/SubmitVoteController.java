package com.dept.java.demo.web.api;

import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import com.dept.java.demo.application.usecases.vote.SubmitVoteCommand;
import com.dept.java.demo.application.usecases.vote.VoteRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polls/{pollId}/vote")
@RequiredArgsConstructor
@Validated
public class SubmitVoteController {

    private final SubmitVoteCommand submitVoteCommand;

    @PostMapping
    public ResponseEntity<String> submitPoll(@PathVariable("pollId") UUID pollId,
        @RequestBody VoteRequest voteRequest)
        throws ValidationFailed {
        voteRequest.setPollId(pollId);
        submitVoteCommand.execute(voteRequest);
        return ResponseEntity.noContent()
                             .build();
    }
}
