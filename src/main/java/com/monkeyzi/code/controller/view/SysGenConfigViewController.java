package com.monkeyzi.code.controller.view;

import com.monkeyzi.code.constant.CommonConstants;
import com.monkeyzi.code.entity.SysDatasourceConf;
import com.monkeyzi.code.entity.SysGenConfig;
import com.monkeyzi.code.service.DataSourceConfService;
import com.monkeyzi.code.service.SysGenConfigService;
import com.monkeyzi.code.utils.NpmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * @author: 高yg
 * @date: 2020/1/5 10:09
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@Controller
@RequestMapping(CommonConstants.VIEW_PREFIX)
public class SysGenConfigViewController {


    @Autowired
    private SysGenConfigService sysGenConfigService;

    @GetMapping("code/config")
    @RequiresPermissions("config:view")
    public String generator(Model model) {
        List<SysGenConfig> config = sysGenConfigService.list();
        final boolean present = Optional.of(config).isPresent();
        model.addAttribute("config", present?config.get(0):null);
        return NpmsUtils.view("gen/genConfig");
    }




}
