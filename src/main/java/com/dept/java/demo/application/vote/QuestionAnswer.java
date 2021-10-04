package com.dept.java.demo.application.vote;

import lombok.Data;

import java.util.UUID;

@Data
public class QuestionAnswer {
    private UUID questionId;
    private UUID choosenOptionId;
}