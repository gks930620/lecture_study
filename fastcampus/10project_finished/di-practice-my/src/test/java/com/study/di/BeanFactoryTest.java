package com.study.di;

import com.study.annotation.Controller;
import com.study.annotation.Service;
import com.study.controller.UserController;
import com.study.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {

    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        reflections = new Reflections("com.study");
        Set<Class<?>> preInstantiactedClazz = getTypesAnnotatedWith(Controller.class, Service.class);
        beanFactory = new BeanFactory(preInstantiactedClazz);
    }

    //? 어떤 타입이든 다 가능. 서로 다른 타입이 와도 상관 없음
    //T  어떤 타입이든 가능하지만, 한번 정해진 타입 그대로 전달

    // Controller, Service 뭐가 올지 모름.   ?
        //T : BeanFactory 참고
    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
            Set<Class<?>> beans = new HashSet<>();
            for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));  //@Controller가붙은 모든 클래스 + Service가 붙은 모든 클래스
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


