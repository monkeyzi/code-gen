package com.monkeyzi.code.controller;

import com.monkeyzi.code.auth.ShiroHelper;
import com.monkeyzi.code.base.BaseController;
import com.monkeyzi.code.base.Result;
import com.monkeyzi.code.constant.CommonConstants;
import com.monkeyzi.code.entity.SysUser;
import com.monkeyzi.code.service.SysUserService;
import com.monkeyzi.code.utils.NpmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:52
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
@Slf4j
@Controller
public class IndexController extends BaseController {
    @Autowired
    private ShiroHelper shiroHelper;

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        SysUser user = super.getCurrentUser();
        SysUser currentUserDetail = sysUserService.getById(user.getId());
        currentUserDetail.setPassword("不能告诉你哦！");
        model.addAttribute("user", currentUserDetail);
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles", authorizationInfo.getRoles());
        return "index";
    }
    @GetMapping("index/{id}")
    @ResponseBody
    public Result index(@NotBlank(message = "{required}") @PathVariable Long id) {
        Map<String, Object> data = new HashMap<>(8);
        // 获取系统访问记录
        data.put("totalVisitCount", 0);
        data.put("todayVisitCount", 0);
        data.put("todayIp", 0);
        // 获取近期系统访问记录
        data.put("lastSevenVisitCount", 0);
        data.put("lastSevenUserVisitCount", 0);
        return Result.ok(data);
    }
    @GetMapping(CommonConstants.VIEW_PREFIX + "layout")
    public String layout() {
        return NpmsUtils.view("layout");
    }


    @GetMapping(CommonConstants.VIEW_PREFIX + "404")
    public String error404() {
        return NpmsUtils.view("error/404");
    }

    @GetMapping(CommonConstants.VIEW_PREFIX + "403")
    public String error403() {
        return NpmsUtils.view("error/403");
    }

    @GetMapping(CommonConstants.VIEW_PREFIX + "500")
    public String error500() {
        return NpmsUtils.view("error/500");
    }

    @RequestMapping(CommonConstants.VIEW_PREFIX + "index")
    public String pageIndex() {
        return NpmsUtils.view("index");
    }

    @GetMapping(CommonConstants.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return NpmsUtils.view("user/userProfile");
    }

}
