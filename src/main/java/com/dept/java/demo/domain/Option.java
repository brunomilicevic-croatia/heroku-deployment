package com.dept.java.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import lombok.Data;

@Data
public class Option {

    private UUID id;
    @JsonIgnore
    private Question question;
    private String text;
}
