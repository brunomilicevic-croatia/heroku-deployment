package com.dept.java.demo.domain;

import com.dept.java.demo.domain.polls.Option;
import com.dept.java.demo.domain.polls.Poll;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class Question {

    private UUID id;
    private String text;
    private List<Option> options = new ArrayList<>();
    @JsonIgnore
    private Poll poll;

}
