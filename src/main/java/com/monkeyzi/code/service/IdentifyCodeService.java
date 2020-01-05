package com.monkeyzi.code.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 高yg
 * @date: 2019/12/1 22:01
 * @className:IdentifyCodeService
 * @description: 验证码处理service
 */
public interface IdentifyCodeService {
    /**
     * 生成图形验证码
     * @param response
     * @param request
     * @throws Exception
     */
    void genCaptcha(HttpServletResponse response, HttpServletRequest  request) throws Exception;

    /**
     * 校验验证码
     * @param verifyCode
     * @param request
     * @return
     */
    boolean verify(String verifyCode, HttpServletRequest request);
}
