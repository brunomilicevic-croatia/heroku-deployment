package com.dept.java.demo.application.usecases.publishing;

import com.dept.java.demo.application.common.annotations.UseCase;
import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.Poll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class PublishPublicPollCommand implements UseCase<Void, PublishRequest> {

    private final PollRepository pollRepository;

    public boolean supports(PublishRequest publishRequest) {
        return CollectionUtils.isEmpty(publishRequest.getRecipientList());
    }

    @Override
    public Void execute(PublishRequest publishRequest) throws ValidationFailed {
        validate(publishRequest);
        Poll poll = pollRepository.findById(publishRequest.getId())
                                  .orElseThrow(() -> new IllegalStateException("Poll not found"));
        poll.setPublishFrom(publishRequest.getPublishFrom());
        poll.setPublishTo(publishRequest.getPublishTo());
        pollRepository.save(poll);
        return null;
    }

    @Override
    public void validate(PublishRequest requestModel) throws ValidationFailed {
        if (requestModel.getPublishFrom().isAfter(requestModel.getPublishTo()) || requestModel.getPublishFrom().equals(requestModel.getPublishTo())) {
            throw new ValidationFailed("Published from date has to be before published to");
        }
    }
}
