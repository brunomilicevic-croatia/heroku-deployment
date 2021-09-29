package com.dept.java.demo.application.polls;

import com.dept.java.demo.application.UseCase;
import com.dept.java.demo.domain.polls.Poll;

import java.util.Optional;
import java.util.UUID;

public class GetPollById implements UseCase<Optional<Poll>, UUID> {

    @Override
    public Optional<Poll> execute(UUID pollId) {
        return null;
    }

    @Override
    public void validate(UUID requestModel) {
        // No validations needed
    }
}
