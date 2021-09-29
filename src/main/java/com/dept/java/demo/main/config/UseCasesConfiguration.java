package com.dept.java.demo.main.config;

import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.application.polls.CreatePoll;
import com.dept.java.demo.application.polls.GetAllPolls;
import com.dept.java.demo.application.polls.GetPollById;
import com.dept.java.demo.application.publishing.PollPublisher;
import com.dept.java.demo.infrastructure.repository.PollJpaRepository;
import com.dept.java.demo.infrastructure.repository.PollRepositoryImpl;
import com.dept.java.demo.infrastructure.repository.VoteRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

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
    public GetAllPolls getAllPollsBean() {
        return new GetAllPolls();
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
