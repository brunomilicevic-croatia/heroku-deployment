package com.dept.java.demo.domain;

import java.util.UUID;
import lombok.Value;

@Value
public class AnswerCount {
    private String optionText;
    private UUID optionId;
    private long count;
}
