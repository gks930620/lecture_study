package com.study.di;

import com.study.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {

    public static Constructor<?> getInjectedConsturctors(Class<?> clazz) {
        Set<Constructor> injectedConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
        if(injectedConstructors.isEmpty()) return  null;
        return injectedConstructors.iterator().next();  //첫번째 요소만 return,    어떤 클래스(여기서는 @Controller가 붙은 클래스)의 생성자가 많을 수도 있겠지만,  우리는 한개만 있다고 가정한거지.. 실제로 DI를 위한 생성자는 보통 1개니까.
    }


}
