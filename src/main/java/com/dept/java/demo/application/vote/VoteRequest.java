package com.dept.java.demo.application.vote;

import com.dept.java.demo.domain.QuestionAnswer;
import lombok.Value;

import java.util.Collection;
import java.util.UUID;

@Value
public class VoteRequest {
    private UUID instanceId;
    private Collection<QuestionAnswer> answers;
}
