package com.monkeyzi.code.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OracleCodeGenMapper {
    /**
     * 查询当前库的表信息
     * @param tableName
     * @return
     */
    List<Map<String, Object>> queryList(@Param("tableName") String tableName);
    /**
     * 查询表信息
     *
     * @param tableName 表名称
     * @return
     */
    Map<String, String> queryTable(String tableName);

    /**
     * 查询表列信息
     *
     * @param tableName 表名称
     * @return
     */
    List<Map<String, String>> queryColumns(String tableName);
}
