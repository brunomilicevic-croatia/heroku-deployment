package com.dept.java.demo.infrastructure.repository;

import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.polls.Poll;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

public class PollRepositoryImpl implements PollRepository {

    private final PollJpaRepository jpaRepository;

    public PollRepositoryImpl(PollJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Poll save(Poll poll) {
        return jpaRepository.save(poll);
    }

    @Override
    public Optional<Poll> findById(UUID pollId) {
        return jpaRepository.findById(pollId);
    }

}
