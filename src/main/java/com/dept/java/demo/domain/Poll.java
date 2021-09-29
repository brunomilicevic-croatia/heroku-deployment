package com.dept.java.demo.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "polls")
public class Poll {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poll")
    @Valid
    @ToString.Exclude
    private List<Question> questions;
    private Instant publishFrom;
    private Instant publishTo;

    public boolean isPublished() {
        Instant now = Instant.now();
        return now.isAfter(publishFrom) && now.isBefore(publishTo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Poll poll = (Poll) o;
        return Objects.equals(id, poll.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
