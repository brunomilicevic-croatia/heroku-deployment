package com.dept.java.demo.domain.polls;

import com.dept.java.demo.domain.Question;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Poll {
    private UUID id;
    private String name;
    private String description;
    @ToString.Exclude
    private List<Question> questions;
//    @Transient
//    private Instant publishFrom;
//    @Transient
//    private Instant publishTo;
//
//    public boolean isPublished() {
//        Instant now = Instant.now();
//        return now.isAfter(publishFrom) && now.isBefore(publishTo);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Poll poll = (Poll) o;
        return Objects.equals(id, poll.id) && Objects.equals(name, poll.name)
            && Objects.equals(description, poll.description) && Objects.equals(
            questions, poll.questions);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
