package com.monkeyzi.code.controller;

import com.monkeyzi.code.base.BaseController;
import com.monkeyzi.code.base.Result;
import com.monkeyzi.code.dto.MenuTree;
import com.monkeyzi.code.entity.SysMenu;
import com.monkeyzi.code.entity.SysUser;
import com.monkeyzi.code.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author: 高yg
 * @date: 2020/1/4 19:43
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@RestController
@RequestMapping(value = "menu")
public class SysMenuController  extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;


    @GetMapping("{id}")
    public Result getUserMenus(@NotBlank(message = "{id不能为空}") @PathVariable Long id){
        SysUser currentUser = getCurrentUser();
        MenuTree<SysMenu> userMenus = this.sysMenuService.findUserMenus(id);
        return Result.ok(userMenus);
    }
}
