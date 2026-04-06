package com.study.di;

import com.study.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {

    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        Set<Constructor> injectedConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
        if(injectedConstructors.isEmpty()){
            return null;
        }
        return injectedConstructors.iterator().next();  // @Inject가 붙은 생성자가 하나일거라 생각하고 한개만.
    }

}
