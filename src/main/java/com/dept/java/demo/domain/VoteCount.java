package com.dept.java.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class VoteCount {

    private UUID questionId;
    private String questionText;
    private List<AnswerCount> answerCountList = new ArrayList<>();

}
