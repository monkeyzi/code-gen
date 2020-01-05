package com.monkeyzi.code.controller;

import com.monkeyzi.code.base.BaseController;
import com.monkeyzi.code.base.Result;
import com.monkeyzi.code.service.IdentifyCodeService;
import com.monkeyzi.code.utils.MD5Util;
import com.monkeyzi.code.utils.NpmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

@Slf4j
@Controller
public class LoginController extends BaseController {

    @Autowired
    private IdentifyCodeService identifyCodeService;
    /**
     * 去登录的页面
     * @param request
     * @return
     */
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


    @PostMapping("login")
    @ResponseBody
    public Result login(
            @NotBlank(message = "用户名不能为空") String username,
            @NotBlank(message = "密码不能为空") String password,
            @NotBlank(message = "验证码不能为空") String verifyCode,
            boolean rememberMe, HttpServletRequest request) {
        if (!identifyCodeService.verify(verifyCode, request)) {
            return Result.fail("验证码错误！");
        }
        password = MD5Util.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        super.login(token);
        return Result.ok("ok");
    }
    /**
     * 获取验证码接口
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("images/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
         identifyCodeService.genCaptcha(response,request);
    }


}
