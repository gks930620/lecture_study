package com.study.mvc;

import com.study.annotation.Controller;
import com.study.annotation.RequestMapping;
import com.study.mvc.controller.RequestMethod;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> hadlers = new HashMap<>();

    public AnnotationHandlerMapping(Object ... basePackage) {
        this.basePackage=basePackage;
    }

    public void initialize(){
        Reflections reflections= new Reflections(basePackage);
        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);
        clazzesWithControllerAnnotation.forEach(clazz -> {
            //@Controller붙은것들 중에서

            Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod ->{
                //모든 메소드 중 @RequestMapping이 붙은 메소드만
                RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                Arrays.stream(getRequestMethod(requestMapping))
                        .forEach(requestMethod -> hadlers.put(
                                new HandlerKey(requestMethod,requestMapping.value())
                        ,      new AnnotationHandler(clazz,declaredMethod)     ));

            } );
        });



    }

    private RequestMethod[] getRequestMethod(RequestMapping requestMapping) {
        return  requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return hadlers.get(handlerKey);
    }
}
