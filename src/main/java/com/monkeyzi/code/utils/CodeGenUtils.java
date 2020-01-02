package com.monkeyzi.code.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2020/1/2 22:32
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:代码生成工具类
 */
@Slf4j
@UtilityClass
public class CodeGenUtils {

    private List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("template/generator/Entity.java.vm");
        templates.add("template/generator/Mapper.java.vm");
        templates.add("template/generator/Mapper.xml.vm");
        templates.add("template/generator/Service.java.vm");
        templates.add("template/generator/ServiceImpl.java.vm");
        templates.add("template/generator/Controller.java.vm");
        return templates;
    }





}
