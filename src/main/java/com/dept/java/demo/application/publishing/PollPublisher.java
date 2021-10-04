package com.dept.java.demo.application.publishing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PollPublisher {

    @Value("${test.app.prop}")
    private String testProp;

    private List<Publisher> publisherList = new ArrayList<>();

    public void publish(UUID pollId, PublishRequest publishRequest) {
        publisherList.stream()
                .filter(publisher -> publisher.supports(publishRequest))
                .findFirst()
                .ifPresent(publisher -> publisher.execute(pollId, publishRequest));
    }

    public String getTestProp() {
        return this.testProp;
    }
}
