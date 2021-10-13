package com.dept.java.demo.application.usecases.vote;

import com.dept.java.demo.application.common.annotations.UseCase;
import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.Poll;
import com.dept.java.demo.domain.QuestionAnswer;
import com.dept.java.demo.infrastructure.repository.VoteRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class SubmitVoteCommand implements UseCase<Void, VoteRequest> {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    public SubmitVoteCommand(PollRepository pollRepository, VoteRepository voteRepository) {
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Void execute(VoteRequest voteRequest) throws ValidationFailed {
        validate(voteRequest);
        Optional<Poll> pollOptional = pollRepository.findById(voteRequest.getPollId());
        voteRepository.saveAnswers(voteRequest.getInstanceId(), voteRequest.getAnswers());
        return null;
    }

    @Override
    public void validate(VoteRequest voteRequest) throws ValidationFailed {
        if (CollectionUtils.isEmpty(voteRequest.getAnswers())) {
            throw new ValidationFailed("Please add answers");
        }
        List<QuestionAnswer> noAnswer = voteRequest.getAnswers()
                                                   .stream()
                                                   .filter(questionAnswer ->
                                                       questionAnswer.getChoosenOptionId() == null)
                                                   .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(noAnswer)) {
            throw new ValidationFailed("Now all question have answers");
        }
    }
}
