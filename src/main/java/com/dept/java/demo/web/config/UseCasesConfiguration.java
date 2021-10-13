package com.dept.java.demo.web.config;

import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.application.usecases.polls.CreatePollCommand;
import com.dept.java.demo.application.usecases.polls.QueryAllPolls;
import com.dept.java.demo.application.usecases.polls.QueryPollById;
import com.dept.java.demo.application.usecases.publishing.PublishClosedPollCommand;
import com.dept.java.demo.application.usecases.publishing.PublishPublicPollCommand;
import com.dept.java.demo.application.usecases.results.QueryPollResults;
import com.dept.java.demo.application.usecases.vote.SubmitVoteCommand;
import com.dept.java.demo.infrastructure.repository.PollJpaRepository;
import com.dept.java.demo.infrastructure.repository.PollRepositoryImpl;
import com.dept.java.demo.infrastructure.repository.VoteRepository;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfiguration {

    @Bean
    public PollRepository createPollRepositoryBean(PollJpaRepository jpaRepository,
        DataSource dataSource) {
        return new PollRepositoryImpl(jpaRepository, dataSource);
    }

    @Bean
    public CreatePollCommand createPollBean(PollRepository pollRepository) {
        return new CreatePollCommand(pollRepository);
    }

    @Bean
    public QueryAllPolls createAllPollsBean(PollRepository pollRepository) {
        return new QueryAllPolls(pollRepository);
    }

    @Bean
    public QueryPollById createPollByIdBean() {
        return new QueryPollById();
    }

    @Bean
    public PublishClosedPollCommand createPublishClosedPollBean() {
        return new PublishClosedPollCommand();
    }

    @Bean
    public PublishPublicPollCommand createPublishPublicPollBean(PollRepository pollRepository) {
        return new PublishPublicPollCommand(pollRepository);
    }

    @Bean
    public QueryPollResults createQueryPollResultsBean(VoteRepository voteRepository,
        PollRepository pollRepository) {
        return new QueryPollResults(voteRepository, pollRepository);
    }

    @Bean
    public SubmitVoteCommand createSubmitVoteBean(PollRepository pollRepository,
        VoteRepository voteRepository) {
        return new SubmitVoteCommand(pollRepository, voteRepository);
    }
}
