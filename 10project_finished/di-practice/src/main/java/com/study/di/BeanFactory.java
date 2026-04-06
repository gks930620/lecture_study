package com.study.di;

import com.study.annotation.Inject;
import com.study.controller.UserController;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private final Set<Class<?>> preInstantiatedClazz;
    private Map<Class<?>, Object> beans= new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz=preInstantiatedClazz;
        initialize();
    }

    private void initialize() {
        for(Class<?> clazz : preInstantiatedClazz){
            Object instance= createInstance(clazz);
            beans.put(clazz,instance);
        }
    }

    private Object createInstance(Class<?> clazz) {
        //생성자
        Constructor<?> constructor=findConstructor(clazz);

        //파라미터
        List<Object> parameters= new ArrayList<>();
        for(Class<?> typeClass : constructor.getParameterTypes()){
            parameters.add( getParameterByClass(typeClass)  );
        }

        //인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());  //생성자를 통해 객체생성
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> constructor =BeanFactoryUtils.getInjectedConsturctors(clazz);
        if(Objects.nonNull(constructor)){
            return constructor;  // @Inject가 있으면 @Inject붙은 constructor중 첫번째 constructor
        }
        //@Inject가 없으면 constructor =null  그럴때는 null이 아니라

        return  clazz.getConstructors()[0];  //  아무 constructor 중 첫번째 (보통 그냥 기본 생성자가 될것 )
    }

    private Object getParameterByClass(Class<?> typeClass) {
        Object instanceBean = getBean(typeClass);
        if(Objects.nonNull(instanceBean)) return  instanceBean;

        return createInstance(typeClass); //재귀
        //첫번째는 Controllr가 옴.  Constructor 생성중  UserService가 필요함.
        // UserService를 생성하도록 함.  생성한 UserService객체를 Controller의 파라미터로 전달.
        // 만약 UserService에서도 다른 객체가 필요하다면 그 객체 생성하도록 하겠지..

    }






    public <T> T getBean(Class<?> requiredType) {
        return  (T) beans.get(requiredType);
    }
}
