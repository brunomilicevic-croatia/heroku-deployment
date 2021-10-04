package com.dept.java.demo.application.polls;

import com.dept.java.demo.application.UseCase;
import com.dept.java.demo.application.ValidationFailed;
import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.polls.Poll;

public class CreatePoll implements UseCase<Poll, Poll> {

    private final PollRepository repository;

    public CreatePoll(PollRepository repository) {
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

    }

}
