package com.dept.java.demo.application.usecases.results;

import com.dept.java.demo.application.common.annotations.UseCase;
import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.Poll;
import com.dept.java.demo.domain.VoteCount;
import com.dept.java.demo.infrastructure.repository.VoteRepository;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class QueryPollResults implements UseCase<Collection<VoteCount>, UUID> {

    private final VoteRepository voteRepository;
    private final PollRepository pollRepository;

    public QueryPollResults(
        VoteRepository voteRepository,
        PollRepository pollRepository) {
        this.voteRepository = voteRepository;
        this.pollRepository = pollRepository;
    }

    @Override
    public Collection<VoteCount> execute(UUID pollId) throws ValidationFailed {
        Poll poll = pollRepository.findById(pollId)
                                  .orElseThrow(() -> new IllegalArgumentException(
                                      "Poll not found"));
        return poll.getQuestions()
                   .stream()
                   .map(
                       question -> voteRepository.getQuestionVotesCount(
                           question.getId()))
                   .collect(Collectors.toList());
    }

}
