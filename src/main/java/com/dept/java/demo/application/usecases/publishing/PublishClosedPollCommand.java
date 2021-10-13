package com.dept.java.demo.application.usecases.publishing;

import com.dept.java.demo.application.common.annotations.UseCase;
import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class PublishClosedPollCommand implements UseCase<Void, PublishRequest> {


    public boolean supports(PublishRequest publishRequest) {
        return !CollectionUtils.isEmpty(publishRequest.getRecipientList());
    }

    @Override
    public Void execute(PublishRequest publishRequest) throws ValidationFailed {
        validate(publishRequest);
        // TODO publish the poll and send email to all recipients
        return null;
    }

    @Override
    public void validate(PublishRequest publishRequest) throws ValidationFailed {
        if (!supports(publishRequest)) {
            throw new ValidationFailed("Poll cannot be publish as public poll");
        }
        for (String email : publishRequest.getRecipientList()) {
            if (!isValid(email, null)) {
                throw new ValidationFailed(String.format("Email %s is not valid", email));
            }
        }
    }

    private boolean isValid(String email, Object o) {
        // TODO implement this
        return true;
    }

}
