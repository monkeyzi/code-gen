package com.monkeyzi.code.controller;

import com.monkeyzi.code.base.BaseController;
import com.monkeyzi.code.base.Result;
import com.monkeyzi.code.entity.SysGenConfig;
import com.monkeyzi.code.service.SysGenConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author: 高yg
 * @date: 2020/1/5 15:50
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("code/config")
public class SysGenConfigController extends BaseController {

    @Autowired
    private SysGenConfigService sysGenConfigService;

    @PostMapping(value = "/update")
    public Result saveOrUpdate(SysGenConfig config){
        if (config.getId()!=null){
            config.setUpdateTime(LocalDateTime.now());
        }else {
            config.setCreateTime(LocalDateTime.now());
        }
        this.sysGenConfigService.saveOrUpdate(config);
        return Result.ok("成功");
    }

}
