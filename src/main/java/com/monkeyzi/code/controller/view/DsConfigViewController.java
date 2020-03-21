package com.monkeyzi.code.controller.view;

import com.monkeyzi.code.constant.CommonConstants;
import com.monkeyzi.code.entity.SysDatasourceConf;
import com.monkeyzi.code.service.DataSourceConfService;
import com.monkeyzi.code.utils.NpmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 高yg
 * @date: 2020/1/5 10:09
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
@Slf4j
@Controller
@RequestMapping(CommonConstants.VIEW_PREFIX)
public class DsConfigViewController {

    @Autowired
    private DataSourceConfService dataSourceConfService;

    @GetMapping("ds")
    @RequiresPermissions("ds:view")
    public String generator() {
        return NpmsUtils.view("ds/dataSource");
    }

    @GetMapping("ds/update/{id}")
    @RequiresPermissions("ds:update")
    public String update(@PathVariable Long id, Model model) {
        SysDatasourceConf ds=this.dataSourceConfService.getById(id);
        model.addAttribute("ds",ds);
        return NpmsUtils.view("ds/dsUpdate");
    }
    @GetMapping("ds/add")
    @RequiresPermissions("ds:add")
    public String update() {
        return NpmsUtils.view("ds/dsAdd");
    }

}
