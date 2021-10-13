package com.dept.java.demo.infrastructure.repository;

import com.dept.java.demo.domain.Poll;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;


@Repository
public interface PollJpaRepository {

    Poll save(Poll poll);

    Optional<Poll> findById(UUID pollId);

    List<Poll> findAll();
}
