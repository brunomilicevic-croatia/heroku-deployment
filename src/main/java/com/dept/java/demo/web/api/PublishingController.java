package com.dept.java.demo.web.api;

import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import com.dept.java.demo.application.usecases.publishing.PublishPublicPollCommand;
import com.dept.java.demo.application.usecases.publishing.PublishRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("polls/{pollId}/publish")
@RequiredArgsConstructor
public class PublishingController {

    private final PublishPublicPollCommand publishClosedPollCommand;
    private final PublishPublicPollCommand publishPublicPollCommand;

    @PostMapping
    public ResponseEntity<Void> publish(@PathVariable("pollId") UUID pollId,
        @RequestBody PublishRequest publishRequest)
        throws ValidationFailed {
        publishRequest.setId(pollId);
        if (publishClosedPollCommand.supports(publishRequest)) {
            publishClosedPollCommand.execute(publishRequest);
            return ResponseEntity.noContent()
                                 .build();
        } else if (publishPublicPollCommand.supports(publishRequest)) {
            publishPublicPollCommand.execute(publishRequest);
            return ResponseEntity.noContent()
                                 .build();
        } else {
            return ResponseEntity.badRequest()
                                 .build();
        }
    }
}
