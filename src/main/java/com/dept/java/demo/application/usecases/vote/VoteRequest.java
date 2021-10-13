package com.dept.java.demo.application.usecases.vote;

import com.dept.java.demo.domain.QuestionAnswer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Collection;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {
    private UUID instanceId;
    private Collection<QuestionAnswer> answers;
    @JsonIgnoreProperties
    private UUID pollId;
}
