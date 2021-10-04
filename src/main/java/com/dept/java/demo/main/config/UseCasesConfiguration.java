package com.dept.java.demo.main.config;

import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.application.polls.CreatePoll;
import com.dept.java.demo.application.polls.GetAllPolls;
import com.dept.java.demo.application.polls.GetPollById;
import com.dept.java.demo.application.publishing.PollPublisher;
import com.dept.java.demo.infrastructure.repository.PollJpaRepository;
import com.dept.java.demo.infrastructure.repository.PollRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfiguration {

    @Bean
    public PollRepository pollRepositoryBean(PollJpaRepository jpaRepository) {
        return new PollRepositoryImpl(jpaRepository);
    }

    @Bean
    public CreatePoll createPollBean(PollRepository pollRepository) {
        return new CreatePoll(pollRepository);
    }

    @Bean
    public GetAllPolls getAllPollsBean(PollRepository pollRepository) {
        return new GetAllPolls(pollRepository);
    }

    @Bean
    public GetPollById getPollByIdBean() {
        return new GetPollById();
    }

    @Bean
    public PollPublisher pollPublisherBean() {
        return new PollPublisher();
    }
}
