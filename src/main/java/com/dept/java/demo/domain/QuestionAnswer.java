package com.dept.java.demo.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class QuestionAnswer {
    private UUID questionId;
    private UUID choosenOptionId;
}
