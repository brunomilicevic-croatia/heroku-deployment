package com.dept.java.demo.application.polls;

import com.dept.java.demo.application.UseCase;
import com.dept.java.demo.application.ValidationFailed;
import com.dept.java.demo.domain.Poll;

public class CreateNew implements UseCase<Poll, Poll> {

    @Override
    public Poll execute(Poll requestModel) throws ValidationFailed {
        validate(requestModel);
        return null;
    }

    @Override
    public void validate(Poll requestModel) throws ValidationFailed {

    }

}
