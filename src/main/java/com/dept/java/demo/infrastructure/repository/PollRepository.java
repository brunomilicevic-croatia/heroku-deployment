package com.dept.java.demo.infrastructure.repository;

import com.dept.java.demo.domain.Poll;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PollRepository extends CrudRepository<Poll, UUID> {
}
