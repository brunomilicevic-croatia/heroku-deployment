package com.dept.java.demo.infrastructure.repository;

import com.dept.java.demo.domain.polls.Option;
import com.dept.java.demo.domain.polls.Poll;
import com.dept.java.demo.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PollRepositoryJDBC {

    private final DataSource dataSource;

    private final String ADD_POLL_SQL = "insert into polls (id, name, description) values (?, ?, ?)";
    private final String ADD_QUESTION = "insert into questions (id, text, poll_id) values (?, ?, ?)";
    private final String ADD_QUESTION_OPTION = "insert into options (id, text, question_id) values (?, ?, ?)";

    public Poll add(Poll newPoll) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement savePoll = connection.prepareStatement(ADD_POLL_SQL);
             PreparedStatement addQuestion = connection.prepareStatement(ADD_QUESTION);
             PreparedStatement addOption = connection.prepareStatement(ADD_QUESTION_OPTION)) {
            newPoll.setId(UUID.randomUUID());
            savePoll.setObject(1, newPoll.getId());
            savePoll.setString(2, newPoll.getName());
            savePoll.setString(3, newPoll.getDescription());
            savePoll.execute();
            for (Question question: newPoll.getQuestions()) {
                question.setId(UUID.randomUUID());
                addQuestion.setObject(1, question.getId());
                addQuestion.setString(2, question.getText());
                addQuestion.setObject(3, newPoll.getId());
                addQuestion.addBatch();
                for (Option option : question.getOptions()) {
                    option.setId(UUID.randomUUID());
                    addOption.setObject(1, option.getId());
                    addOption.setString(2, option.getText());
                    addOption.setObject(3, question.getId());
                    addOption.addBatch();
                }
            }
            addQuestion.executeBatch();
            addOption.executeBatch();
            return newPoll;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Failed to save poll");
        }
    }
}
