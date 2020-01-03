package com.monkeyzi.code.service.impl;

import cn.hutool.core.io.IoUtil;
import com.monkeyzi.code.dto.GenConfigDto;
import com.monkeyzi.code.holder.DynamicDataSourceContextHolder;
import com.monkeyzi.code.mapper.MysqlCodeGenMapper;
import com.monkeyzi.code.service.CodeGenService;
import com.monkeyzi.code.utils.CodeGenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
public class CodeGenServiceImpl implements CodeGenService {

    @Autowired
    private MysqlCodeGenMapper mysqlCodeGenMapper;


    @Override
    public byte[] generatorCode(GenConfigDto genConfig) {
        DynamicDataSourceContextHolder.setDataSourceType(genConfig.getId());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        //查询表信息
        Map<String, String> table = mysqlCodeGenMapper.queryTable(genConfig.getTableName());
        //查询列信息
        List<Map<String, String>> columns = mysqlCodeGenMapper.queryColumns(genConfig.getTableName());
        //生成代码
        CodeGenUtils.generatorCode(genConfig, table, columns, zip);
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }
}
