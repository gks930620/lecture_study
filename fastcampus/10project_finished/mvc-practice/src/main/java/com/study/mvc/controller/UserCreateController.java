package com.study.mvc.controller;

import com.study.mvc.model.User;
import com.study.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController  implements  Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserRepository.save(new User(request.getParameter("userId"),request.getParameter("name") ));
        return "redirect:/users";
    }
}
