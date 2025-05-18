package com.study.mvc;

import com.study.mvc.controller.Controller;
import com.study.mvc.controller.RequestMethod;
import com.study.mvc.view.JspViewResolver;
import com.study.mvc.view.ModelAndView;
import com.study.mvc.view.View;
import com.study.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerMapping> handlerMappings;

    private List<ViewResolver> viewResolvers;

    private List<HandlerAdapter> handlerAdapters;

    @Override
    public void init() throws ServletException {
        RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
        rmhm.init();
        AnnotationHandlerMapping ahm
                = new AnnotationHandlerMapping("com.study");
        ahm.initialize();

        handlerMappings = List.of(rmhm, ahm);

        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());

        viewResolvers = Collections.singletonList(new JspViewResolver()); //나머지도 전부 추가해줘야되지만 일단 한개만
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("DispatcherServlet service started");
        String requestURI = req.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(req.getMethod());

        try {

            Object handler = handlerMappings.stream()
                    .filter(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)) != null)
                    .map(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI))).findFirst()
                    .orElseThrow(() -> new ServletException("no Handler for " + requestMethod + "," + requestURI));

            HandlerAdapter handlerAdapter = handlerAdapters.stream().filter(ha -> ha.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No Adapter for Handler " + handler));

            ModelAndView mav = handlerAdapter.handle(req, resp, handler);

            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(mav.getViewName());
                view.render(mav.getModel(), req, resp);
            }
        } catch (Exception e) {
            log.error("excetpion occured : {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }
}
