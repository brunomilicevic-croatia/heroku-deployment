package com.dept.java.demo.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class AnswerCount {
    private String optionText;
    private UUID optionId;
    private long count;
}
