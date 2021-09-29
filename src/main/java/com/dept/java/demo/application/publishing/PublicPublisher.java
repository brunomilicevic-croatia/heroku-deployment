package com.dept.java.demo.application.publishing;

import com.dept.java.demo.domain.polls.Poll;
import com.dept.java.demo.application.common.interfaces.PollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PublicPublisher implements Publisher {

    private final PollRepository pollRepository;

    @Override
    public void execute(UUID pollId, PublishRequest publishRequest) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new IllegalStateException("Poll not found"));
        poll.setPublishFrom(publishRequest.getPublishFrom());
        poll.setPublishTo(publishRequest.getPublishTo());
        pollRepository.save(poll);
    }

    @Override
    public boolean supports(PublishRequest publishRequest) {
        return CollectionUtils.isEmpty(publishRequest.getRecipientList());
    }

}
