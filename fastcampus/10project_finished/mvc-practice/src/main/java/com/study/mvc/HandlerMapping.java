package com.study.mvc;

import com.study.mvc.controller.Controller;

public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey);
}
