package com.study.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

public class JspView  implements View{
    private final String name;

    public JspView(String name) {
        this.name = name;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //model.forEach((s, o) -> request.setAttribute(s, o));
        model.forEach(request::setAttribute);   // 파라미터로 들어갈  람다가 어떤거든(BitConsumer의 accept() ) 간에 그 안에서 단순히 하나의 메소드만 실행하는게 목적이라면....
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(name);
        requestDispatcher.forward(request,response);
    }
}
