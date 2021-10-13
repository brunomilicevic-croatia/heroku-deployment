package com.dept.java.demo.infrastructure.repository;

import com.dept.java.demo.domain.AnswerCount;
import com.dept.java.demo.domain.QuestionAnswer;
import com.dept.java.demo.domain.VoteCount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VoteRepository {

    private final DataSource dataSource;

    private static final String ADD_VOTE_SQL = "INSERT INTO votes (question_id, option_id, vote_instance_id) values (?, ?, ?)";
    private static final String SUM_QUESTION_VOTES_SQL =
        "select v.question_id, q.\"text\" as question, v.option_id, o.\"text\" as answer, count(o.id)  from votes v "
            + "left join questions q on q.id = v.question_id "
            + "left join \"options\" o ON  o.id =  v.option_id "
            + "where q.id = ? "
            + "group by v.question_id, v.option_id, q.\"text\" , o.\"text\"";

    public void saveAnswers(UUID instanceId, Collection<QuestionAnswer> questionAnswerList) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement addVote = connection.prepareStatement(ADD_VOTE_SQL)) {
            for (QuestionAnswer questionAnswer : questionAnswerList) {
                addVote.setObject(1, questionAnswer.getQuestionId());
                addVote.setObject(2, questionAnswer.getChoosenOptionId());
                addVote.setObject(3, instanceId);
                addVote.addBatch();
            }
            addVote.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new IllegalStateException("Failed to save votes: " + throwables.getMessage());
        }
    }

    public VoteCount getQuestionVotesCount(UUID questionId) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement getVoteCount = connection.prepareStatement(SUM_QUESTION_VOTES_SQL)
        ) {
            getVoteCount.setObject(1, questionId);
            ResultSet resultSet = getVoteCount.executeQuery();
            VoteCount voteCount = new VoteCount();
            while (resultSet.next()) {
                UUID optionId = resultSet.getObject("option_id", UUID.class);
                String question = resultSet.getString("question");
                String answer = resultSet.getString("answer");
                long count = resultSet.getLong("count");
                voteCount.setQuestionId(questionId);
                voteCount.setQuestionText(question);
                voteCount.getAnswerCountList()
                         .add(new AnswerCount(answer, optionId, count));
            }
            return voteCount;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new IllegalStateException("failed to get vote count: " + throwables.getMessage());
        }
    }
}
