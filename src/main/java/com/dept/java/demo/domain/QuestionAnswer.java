package com.dept.java.demo.domain;

import java.util.UUID;
import lombok.Data;

@Data
public class QuestionAnswer {

    private UUID questionId;
    private UUID choosenOptionId;
}
