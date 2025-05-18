package com.study.mvc;

import com.study.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping  implements  HandlerMapping{
    //   /users -> UserController 실행    key : String, value : UserController구현체
    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    public void init() {
//        mappings.put(new HandlerKey(RequestMethod.GET,"/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET,"/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMethod.POST,"/users"), new UserCreateController());
        mappings.put(new HandlerKey(RequestMethod.GET,"/user/form"), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey handlerKey) {
        return mappings.get(handlerKey);
    }

}
