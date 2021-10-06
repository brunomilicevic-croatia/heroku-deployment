package com.dept.java.demo.domain.polls;

import com.dept.java.demo.domain.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.UUID;

@Data
public class Option {

    private UUID id;
    @JsonIgnore
    private Question question;
    private String text;
}
