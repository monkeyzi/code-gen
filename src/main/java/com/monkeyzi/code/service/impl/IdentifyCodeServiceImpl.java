package com.monkeyzi.code.service.impl;

import com.monkeyzi.code.constant.CommonConstants;
import com.monkeyzi.code.service.IdentifyCodeService;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * @author: 高yg
 * @date: 2020/1/3 22:21
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
@Service
@Slf4j
public class IdentifyCodeServiceImpl  implements IdentifyCodeService {

    @Autowired
    private RedisTemplate redisTemplate;

    public static  final int DEFAULT_CODE_HEIGHT=45;
    public static  final int DEFAULT_CODE_WIDTH=120;
    @Override
    public void genCaptcha(HttpServletResponse response, HttpServletRequest request) throws Exception {
        //使用算术验证码
        ArithmeticCaptcha arithmeticCaptcha= new ArithmeticCaptcha();
        arithmeticCaptcha.setHeight(DEFAULT_CODE_HEIGHT);
        arithmeticCaptcha.setWidth(DEFAULT_CODE_WIDTH);
        arithmeticCaptcha.setCharType(5);
        arithmeticCaptcha.setLen(2);
        arithmeticCaptcha.getArithmeticString();
        String codeText=arithmeticCaptcha.text();
        System.out.println(codeText);
        CaptchaUtil.setHeader(response);
        //将deviceId和验证码存入到redis
        String key = CommonConstants.CODE_PREFIX + request.getSession().getId();
        this.redisTemplate.opsForValue().set(key,codeText,120000,TimeUnit.MILLISECONDS);
        arithmeticCaptcha.out(response.getOutputStream());
    }

    @Override
    public boolean verify(String verifyCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String key = CommonConstants.CODE_PREFIX + session.getId();
        String sessionCode = (String) redisTemplate.opsForValue().get(key);
        return StringUtils.equalsIgnoreCase(verifyCode, sessionCode);
    }
}
