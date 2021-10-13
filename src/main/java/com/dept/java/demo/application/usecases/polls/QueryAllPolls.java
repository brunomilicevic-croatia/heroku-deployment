package com.dept.java.demo.application.usecases.polls;

import com.dept.java.demo.application.common.annotations.UseCase;
import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.Poll;
import java.util.List;

public class QueryAllPolls implements UseCase<List<Poll>, Void> {

    private final PollRepository pollRepository;

    public QueryAllPolls(PollRepository pollRepository) {
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
