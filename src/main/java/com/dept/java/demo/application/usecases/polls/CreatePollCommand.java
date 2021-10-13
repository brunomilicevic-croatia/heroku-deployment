package com.dept.java.demo.application.usecases.polls;

import com.dept.java.demo.application.common.annotations.UseCase;
import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.Poll;

public class CreatePollCommand implements UseCase<Poll, Poll> {

    private final PollRepository repository;

    public CreatePollCommand(PollRepository repository) {
        this.repository = repository;
    }

    @Override
    public Poll execute(Poll requestModel) throws ValidationFailed {
        validate(requestModel);
        Poll saved = repository.save(requestModel);
        return saved;
    }

    @Override
    public void validate(Poll requestModel) throws ValidationFailed {
        // validate model
    }

}
