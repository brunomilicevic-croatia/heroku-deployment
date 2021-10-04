package com.dept.java.demo.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class VoteCount {

    private UUID questionId;
    private String questionText;
    private List<AnswerCount> answerCountList = new ArrayList<>();

}
