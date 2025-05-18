package com.study.di;

import com.study.annotation.Inject;
import com.study.controller.UserController;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private final Set<Class<?>> preInstantiactedClazz;
    private Map<Class<?>, Object> beans = new HashMap<>();


    //beanFactory - 수업중 xml읽어서 저장하던 그거.  GenericXmlApplicationContext의 상위,상위... 클래스
    //결국 이게 생성될때 패키지 전부 스캔해야됨.
    public BeanFactory(Set<Class<?>> preInstantiactedClazz) {
        this.preInstantiactedClazz = preInstantiactedClazz; //@controller랑 @service에 대해서만 initilize할거다.
        initialize();

    }

    //이게 맞는거 같은데?
    private void initialize() {
        for(Class<?> clazz : preInstantiactedClazz){
            // Controller만들다가  파라미터로 service 필요=> service 만듦.그리고 Controller만듬
            // for문으로 다음에 service가 나온다? => 이미 만들어저서 beans에 넣어놨으니까 또 만들필요 없음
            if(getBean(clazz)  ==null){
                createInstance(clazz);//com.study에서 @이 붙은 모든 클래스
            }
        }
    }

    private Object createInstance(Class<?> clazz) {
        //생성자
        Constructor<?> constructor = findConstructor(clazz);

        //파라미터
        List<Object> parameters= new ArrayList<>();
        for(Class<?> typeClass : constructor.getParameterTypes()){
            parameters.add( getParameterByClass(typeClass ));
        }
        //인스턴스 생성
        try {
            Object instance = constructor.newInstance(parameters.toArray());
            beans.put(clazz,instance);
            return  instance;
        } catch (InstantiationException  | IllegalAccessException |InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }



    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);
        if(Objects.nonNull(constructor)) return  constructor;   //@inject 가 있으면 해당 constructor
        return clazz.getConstructors()[0]; //@Inject가 없으면 아무 constructor
    }



    private Object getParameterByClass(Class<?> typeClass) {
        //파라미터의 실제 값들을 찾는데  beanFactory에 등록된 값으로 찾아야함.
        Object instanceBean = getBean(typeClass);
        if(Objects.nonNull(instanceBean))return instanceBean;
        return  createInstance(typeClass);
        //재귀가 일어나지.      UserController만들려는데 필요한 UserService가 없네?.  그럼 UserService먼저 만듬
        //UserService는 파라미터가 없으니까 그냥  newInstance해서 객체 생성 

    }



    // T :  매개변수로 Controller가 오면  Controller리턴
    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }
}
