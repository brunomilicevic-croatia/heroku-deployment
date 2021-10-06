package com.dept.java.demo.infrastructure.repository;

import com.dept.java.demo.application.common.interfaces.PollRepository;
import com.dept.java.demo.domain.polls.Option;
import com.dept.java.demo.domain.polls.Poll;
import com.dept.java.demo.domain.Question;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.PooledConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PollRepositoryJDBC implements PollRepository {

    private final DataSource dataSource;

    private final String ADD_POLL_SQL = "insert into polls (id, name, description) values (?, ?, ?)";
    private final String ADD_QUESTION_SQL = "insert into questions (id, text, poll_id) values (?, ?, ?)";
    private final String ADD_QUESTION_OPTION_SQL = "insert into options (id, text, question_id) values (?, ?, ?)";
    private final String GET_POLL_SQL = "select id, name, description where id=?";
    private final String GET_QUESTIONS_IN_POLL_SQL = "select id, text, poll_id where poll_id=?";
    private final String GET_OPTIONS_IN_QUESTION_SQL = "select id, text, question_id where question_id=?";

    public Poll add(Poll newPoll) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement savePoll = connection.prepareStatement(ADD_POLL_SQL);
             PreparedStatement addQuestion = connection.prepareStatement(ADD_QUESTION_SQL);
             PreparedStatement addOption = connection.prepareStatement(ADD_QUESTION_OPTION_SQL)) {
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

    @Override
    public Poll save(Poll poll) {
        return add(poll);
    }

    @Override
    public Optional<Poll> findById(UUID pollId) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement getPoll = connection.prepareStatement(GET_POLL_SQL);
            PreparedStatement getQuestions = connection.prepareStatement(GET_QUESTIONS_IN_POLL_SQL);
            PreparedStatement getOptions = connection.prepareStatement(GET_OPTIONS_IN_QUESTION_SQL)) {
            Poll poll = executeStmt(getPoll, Poll.class);
            List<Question> questions = executeStmtAsList(getQuestions, Question.class);
            for (Question question: questions) {
                List<Option> options = executeStmtAsList(getOptions, Option.class);
                question.setOptions(options);
            }
            poll.setQuestions(questions);
            return Optional.of(poll);
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Failed to save poll", throwables);
        }
    }

    private <T> List<T> executeStmtAsList(PreparedStatement statement, Class<T> model)
        throws NoSuchMethodException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ResultSet resultSet = statement.executeQuery();
        List<T> result = new ArrayList<>();
        Field[] fields = model.getFields();
        while (resultSet.next()) {
            T newModel = model.getDeclaredConstructor().newInstance();
            for (Field field : fields) {
                field.set(newModel, resultSet.getObject(field.getName()));
            }
            result.add(newModel);
        }
        return result;
    }

    private <T> T executeStmt(PreparedStatement statement, Class<T> model)
        throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ResultSet resultSet = statement.executeQuery();
        T newModel = model.getDeclaredConstructor().newInstance();
        Field[] fields = model.getFields();
        if (resultSet.first()) {
            for (Field field : fields) {
                field.set(newModel, resultSet.getObject(field.getName()));
            }
        }
        return newModel;
    }

    public void invokeSetter(Object obj, String propertyName, Object variableValue)
    {
        PropertyDescriptor pd;
        try {
            pd = new PropertyDescriptor(propertyName, obj.getClass());
            Method setter = pd.getWriteMethod();
            try {
                setter.invoke(obj,variableValue);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

    }

    public void invokeGetter(Object obj, String variableName)
    {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
            Method getter = pd.getReadMethod();
            Object f = getter.invoke(obj);
            System.out.println(f);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Poll> findAll() {
        return null;
    }
}
