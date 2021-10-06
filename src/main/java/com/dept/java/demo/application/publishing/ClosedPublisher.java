package com.dept.java.demo.application.publishing;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.UUID;

@Component
public class ClosedPublisher implements Publisher {

    @Override
    public void execute(UUID pollId, PublishRequest publishRequest) {
        validateEmails(publishRequest.getRecipientList());
        // TODO publish the poll and send email to all recipients
    }

    private void validateEmails(Collection<String> recipientList) {
        for(String email : recipientList) {
            if (!isValid(email, null)) {
                throw new IllegalArgumentException(String.format("Email %s is not valid", email));
            }
        }
    }

    private boolean isValid(String email, Object o) {
        // TODO implement this
        return true;
    }

    @Override
    public boolean supports(PublishRequest publishRequest) {
        return !CollectionUtils.isEmpty(publishRequest.getRecipientList());
    }

}
