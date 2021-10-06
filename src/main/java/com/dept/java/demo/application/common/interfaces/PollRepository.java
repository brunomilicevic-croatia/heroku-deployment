package com.dept.java.demo.application.common.interfaces;

import com.dept.java.demo.domain.polls.Poll;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PollRepository {
    Poll save(Poll poll);

    Optional<Poll> findById(UUID pollId);

    List<Poll> findAll();
}
