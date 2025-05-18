package com.study.mvc;

import com.study.mvc.controller.Controller;
import com.study.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleControllerHandlerAdapter  implements  HandlerAdapter{


    @Override
    public boolean supports(Object handler) {
        return handler instanceof Controller;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((Controller) handler).handleRequest(request, response); // object handler가 Controller의 구현체일 때만 ..

        return new ModelAndView(viewName);
    }
}
