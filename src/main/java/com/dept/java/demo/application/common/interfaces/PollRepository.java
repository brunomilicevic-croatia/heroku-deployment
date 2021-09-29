package com.dept.java.demo.application.common.interfaces;

import com.dept.java.demo.domain.polls.Poll;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PollRepository {
    Poll save(Poll poll);

    Optional<Poll> findById(UUID pollId);
}
