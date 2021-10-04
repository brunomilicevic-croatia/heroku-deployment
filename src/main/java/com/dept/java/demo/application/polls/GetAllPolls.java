package com.dept.java.demo.application.polls;

import com.dept.java.demo.application.UseCase;
import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.polls.Poll;

import java.util.List;

public class GetAllPolls implements UseCase<List<Poll>, Void> {

    private final PollRepository pollRepository;

    public GetAllPolls(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }


    @Override
    public List<Poll> execute(Void requestModel) {
        return pollRepository.findAll();
    }

    @Override
    public void validate(Void requestModel) {
        // No validation needed;
    }
}
