package com.dept.java.demo.main.api;

import com.dept.java.demo.application.vote.VoteRequest;
import com.dept.java.demo.domain.polls.Poll;
import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.infrastructure.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/polls/{pollId}/vote")
@RequiredArgsConstructor
@Validated
public class SubmitVoteController {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    @PostMapping
    public ResponseEntity<String> submitPoll(@PathVariable("pollId") UUID pollId, @Valid @RequestBody VoteRequest voteRequest) {
        Optional<Poll> pollOptional = pollRepository.findById(pollId);
        if (pollOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Poll with id " + pollId + " was not found. Please check id.");
        }
        voteRepository.saveAnswers(voteRequest.getInstanceId(), voteRequest.getAnswers());
        return ResponseEntity.noContent().build();
    }
}
