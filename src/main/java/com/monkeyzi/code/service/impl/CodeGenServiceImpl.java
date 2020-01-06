package com.monkeyzi.code.service.impl;

import cn.hutool.core.io.IoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monkeyzi.code.dto.GenCodeDto;
import com.monkeyzi.code.dto.GenConfigDto;
import com.monkeyzi.code.dto.PageRequest;
import com.monkeyzi.code.entity.SysDatasourceConf;
import com.monkeyzi.code.holder.DynamicDataSourceContextHolder;
import com.monkeyzi.code.mapper.MysqlCodeGenMapper;
import com.monkeyzi.code.mapper.OracleCodeGenMapper;
import com.monkeyzi.code.service.CodeGenService;
import com.monkeyzi.code.service.DataSourceConfService;
import com.monkeyzi.code.utils.CodeGenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
public class CodeGenServiceImpl implements CodeGenService {

    private static final String DEFAULT_DB_TYPE="mysql";
    @Autowired
    private DataSourceConfService dataSourceConfService;
    @Autowired
    private MysqlCodeGenMapper mysqlCodeGenMapper;
    @Autowired
    private OracleCodeGenMapper oracleCodeGenMapper;


    @Override
    public byte[] generatorCode(GenConfigDto genConfig) {
        String dbType=this.getDbType(genConfig.getId());
        DynamicDataSourceContextHolder.setDataSourceType(genConfig.getId());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        //查询表信息
        Map<String, String> table;
        //查询列信息
        List<Map<String, String>> columns;
        if (dbType.equals(DEFAULT_DB_TYPE)){
            table = mysqlCodeGenMapper.queryTable(genConfig.getTableName());
            columns=mysqlCodeGenMapper.queryColumns(genConfig.getTableName());
        }else {
            table = oracleCodeGenMapper.queryTable(genConfig.getTableName());
            columns=oracleCodeGenMapper.queryColumns(genConfig.getTableName());
        }
        //生成代码
        CodeGenUtils.generatorCode(genConfig, table, columns, zip);
        IoUtil.close(zip);
        return outputStream.toByteArray();
    }

    @Override
    public PageInfo pageQuery(PageRequest page, GenCodeDto genCodeDto) {
        String dbType=this.getDbType(genCodeDto.getId());
        DynamicDataSourceContextHolder.setDataSourceType(genCodeDto.getId());
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Map<String, Object>> maps;
        //分数据库类型查询
        if (dbType.equals(DEFAULT_DB_TYPE)){
            maps = this.mysqlCodeGenMapper.queryList(genCodeDto.getTableName());
        }else {
            maps=this.oracleCodeGenMapper.queryList(genCodeDto.getTableName());
        }
        return new PageInfo(maps);

    }

    /**
     * 查询数据源类型
     * @param id
     * @return
     */
    private String  getDbType(Integer id){
        String dbType=DEFAULT_DB_TYPE;
        if (id!=null){
            SysDatasourceConf datasourceConf=this.dataSourceConfService.getById(id);
            boolean flag= Optional.of(datasourceConf).isPresent();
            dbType=flag?datasourceConf.getDbType():dbType;
        }
        return dbType;
    }
}
