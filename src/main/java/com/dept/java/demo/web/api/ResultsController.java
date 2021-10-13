package com.dept.java.demo.web.api;

import com.dept.java.demo.application.common.exceptions.ValidationFailed;
import com.dept.java.demo.application.usecases.results.QueryPollResults;
import com.dept.java.demo.domain.VoteCount;
import java.util.Collection;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polls/{pollId}/results")
@RequiredArgsConstructor
public class ResultsController {

    private final QueryPollResults queryPollResults;

    @GetMapping
    public ResponseEntity<Collection<VoteCount>> getResults(@PathVariable UUID pollId)
        throws ValidationFailed {
        return ResponseEntity.ok(queryPollResults.execute(pollId));
    }
}
