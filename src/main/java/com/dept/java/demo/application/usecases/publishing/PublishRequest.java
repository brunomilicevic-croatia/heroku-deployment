package com.dept.java.demo.application.usecases.publishing;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishRequest {
    private UUID id;
    private Instant publishFrom;
    private Instant publishTo;
    private Collection<String> recipientList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PublishRequest that = (PublishRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(publishFrom,
            that.publishFrom) && Objects.equals(publishTo, that.publishTo)
            && Objects.equals(recipientList, that.recipientList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publishFrom, publishTo, recipientList);
    }
}
