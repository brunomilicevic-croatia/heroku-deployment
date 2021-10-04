package com.dept.java.demo.infrastructure.repository;

import com.dept.java.demo.domain.polls.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PollJpaRepository extends JpaRepository<Poll, UUID> {
}
