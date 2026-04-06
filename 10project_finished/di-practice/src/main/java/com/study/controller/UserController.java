package com.study.controller;

import com.study.annotation.Controller;
import com.study.annotation.Inject;
import com.study.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService(){
        return  userService;   //주입이 잘 되었으면 null이 아닐거고 단순히 그걸 확인하기 위한 메소드
    }

}
