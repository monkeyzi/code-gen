package com.monkeyzi.code.controller;

import com.monkeyzi.code.base.BaseController;
import com.monkeyzi.code.utils.NpmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class LoginController extends BaseController {

    @GetMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        if (NpmsUtils.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(NpmsUtils.view("login"));
            return mav;
        }
    }
}
