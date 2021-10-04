package com.dept.java.demo.application.vote;

import com.dept.java.demo.domain.QuestionAnswer;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.UUID;

@Value
public class VoteRequest {
    private UUID instanceId;
    @NotEmpty
    private Collection<QuestionAnswer> answers;
}
