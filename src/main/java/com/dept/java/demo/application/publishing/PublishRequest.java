package com.dept.java.demo.application.publishing;

import lombok.Data;

import java.time.Instant;
import java.util.Collection;

@Data
public class PublishRequest {
    private Instant publishFrom;
    private Instant publishTo;
    private Collection<String> recipientList;
}
