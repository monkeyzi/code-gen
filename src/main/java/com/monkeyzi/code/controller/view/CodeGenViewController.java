package com.monkeyzi.code.controller.view;

import com.monkeyzi.code.constant.CommonConstants;
import com.monkeyzi.code.service.CodeGenService;
import com.monkeyzi.code.utils.NpmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(CommonConstants.VIEW_PREFIX)
public class CodeGenViewController {

   @Autowired
   private CodeGenService codeGenService;


    @GetMapping("gen/code")
    @RequiresPermissions("gen:view")
    public String generator() {
        return NpmsUtils.view("gen/gen");
    }

}
