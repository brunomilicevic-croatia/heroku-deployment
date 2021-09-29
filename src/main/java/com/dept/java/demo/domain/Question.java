package com.dept.java.demo.domain;

import com.dept.java.demo.domain.polls.Option;
import com.dept.java.demo.domain.polls.Poll;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotEmpty
    private String text;
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @Valid
    private List<Option> options = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Poll poll;

}
