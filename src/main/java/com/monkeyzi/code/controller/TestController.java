package com.monkeyzi.code.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.monkeyzi.code.holder.DynamicDataSourceContextHolder;
import com.monkeyzi.code.mapper.BlogMapper;
import com.monkeyzi.code.mapper.DataSourceConfMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private DataSourceConfMapper dataSourceConfMapper;
    @Autowired
    private BlogMapper blogMapper;

    @GetMapping("index")
    public String index(){
        dataSourceConfMapper.selectList(new QueryWrapper<>()).forEach(a->{
            System.out.println(a.getName());
        });
        return "index";
    }

    @GetMapping("list")
    public String list(){
        DynamicDataSourceContextHolder.setDataSourceType(2);
        blogMapper.selectList(new QueryWrapper<>()).forEach(a->{
            System.out.println(a.getBlogTitle());
        });
        return "list";
    }
}
