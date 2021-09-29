package com.dept.java.demo.main.api;

import com.dept.java.demo.domain.Poll;
import com.dept.java.demo.domain.VoteCount;
import com.dept.java.demo.infrastructure.repository.PollRepository;
import com.dept.java.demo.infrastructure.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/polls/{pollId}/results")
@RequiredArgsConstructor
public class ResultsController {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    @GetMapping
    public ResponseEntity<Collection<VoteCount>> getResults(@PathVariable UUID pollId) {
        Optional<Poll> pollOptional = pollRepository.findById(pollId);
        if (pollOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<VoteCount> voteCountList = pollOptional.get().getQuestions()
                .stream()
                .map(question -> voteRepository.getQuestionVotesCount(question.getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(voteCountList);
    }
}
