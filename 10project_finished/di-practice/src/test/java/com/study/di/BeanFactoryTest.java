package com.study.di;

import com.study.annotation.Controller;
import com.study.annotation.Service;
import com.study.controller.UserController;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {

    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        reflections = new Reflections("com.study");
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class);
        beanFactory=new BeanFactory(preInstantiatedClazz);
    }


    private Set<Class<?>>
    getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans= new HashSet<>();
        for(Class<? extends  Annotation> annotation : annotations){
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);
        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }


}












