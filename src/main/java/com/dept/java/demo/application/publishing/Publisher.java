package com.dept.java.demo.application.publishing;

import java.util.UUID;

public interface Publisher {
    public void execute(UUID pollId, PublishRequest publishRequest);
    public boolean supports(PublishRequest publishRequest);
}
