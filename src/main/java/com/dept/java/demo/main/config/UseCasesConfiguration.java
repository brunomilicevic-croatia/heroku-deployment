package com.dept.java.demo.main.config;

import com.dept.java.demo.application.polls.CreateNew;
import com.dept.java.demo.application.polls.GetAll;
import com.dept.java.demo.application.polls.GetById;
import com.dept.java.demo.application.publishing.PollPublisher;
import com.dept.java.demo.infrastructure.repository.PollRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfiguration implements BeanDefinitionRegistryPostProcessor {

    public static void loadUseCases(ConfigurableApplicationContext context) {
        CreateNew createNew = new CreateNew();
        GetAll getAll = new GetAll();
        GetById getById = new GetById();
        context.getBeanFactory().registerSingleton(CreateNew.class.getName(), createNew);
        context.getBeanFactory().registerSingleton("getAll", getAll);
        context.getBeanFactory().registerSingleton("getById", getById);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        CreateNew createNew = new CreateNew();
        GetAll getAll = new GetAll();
        GetById getById = new GetById();
        PollPublisher pollPublisher = new PollPublisher();
        beanFactory.registerSingleton(CreateNew.class.getName(), createNew);
        beanFactory.registerSingleton(GetAll.class.getName(), getAll);
        beanFactory.registerSingleton(GetById.class.getName(), getById);
        beanFactory.registerSingleton(PollPublisher.class.getName(), pollPublisher);
    }
}
