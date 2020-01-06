package com.monkeyzi.code.controller.view;

import com.monkeyzi.code.constant.CommonConstants;
import com.monkeyzi.code.entity.SysGenConfig;
import com.monkeyzi.code.service.CodeGenService;
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

@Slf4j
@Controller
@RequestMapping(CommonConstants.VIEW_PREFIX)
public class CodeGenViewController {

   @Autowired
   private CodeGenService codeGenService;
    @Autowired
    private SysGenConfigService sysGenConfigService;


    @GetMapping("gen/code")
    @RequiresPermissions("gen:view")
    public String generator() {
        return NpmsUtils.view("gen/gen");
    }

    @GetMapping("gen/genCode")
    @RequiresPermissions("gen:view")
    public String genCode(Model model,Integer id,String tableName) {
        List<SysGenConfig> config = sysGenConfigService.list();
        final boolean present = Optional.of(config).isPresent();
        SysGenConfig con=new SysGenConfig();
        if (present){
            con=config.get(0);
        }
        con.setDsId(id);
        con.setTableName(tableName);
        model.addAttribute("config", con);
        return NpmsUtils.view("gen/genCode");
    }

}
