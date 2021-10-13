package com.dept.java.demo.application.usecases.polls;

import com.dept.java.demo.application.common.annotations.UseCase;
import com.dept.java.demo.domain.Poll;
import java.util.Optional;
import java.util.UUID;

public class QueryPollById implements UseCase<Optional<Poll>, UUID> {

    @Override
    public Optional<Poll> execute(UUID pollId) {
        return null;
    }

    @Override
    public void validate(UUID requestModel) {
        // No validations needed
    }
}
