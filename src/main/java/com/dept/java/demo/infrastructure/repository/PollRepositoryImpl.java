package com.dept.java.demo.infrastructure.repository;

import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.Poll;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PollRepositoryImpl implements PollRepository {

    private final PollJpaRepository jpaRepository;
    private final DataSource dataSource;

    @Override
    public Poll save(Poll poll) {
        return jpaRepository.save(poll);
    }

    @Override
    public Optional<Poll> findById(UUID pollId) {
        return jpaRepository.findById(pollId);
    }

    @Override
    public List<Poll> findAll() {
        return jpaRepository.findAll();
    }

}
