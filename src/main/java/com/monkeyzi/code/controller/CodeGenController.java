package com.monkeyzi.code.controller;

import cn.hutool.core.io.IoUtil;
import com.monkeyzi.code.dto.GenConfigDto;
import com.monkeyzi.code.service.CodeGenService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = "/gen")
public class CodeGenController {

   @Autowired
   private CodeGenService codeGenService;


    @PostMapping("/code")
    @SneakyThrows
    public void generatorCode(@RequestBody GenConfigDto genConfig, HttpServletResponse response) {
        byte[] data = codeGenService.generatorCode(genConfig);
        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genConfig.getTableName()));
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }



}
