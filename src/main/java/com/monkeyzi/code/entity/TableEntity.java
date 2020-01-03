package com.monkeyzi.code.entity;

import lombok.Data;

import java.util.List;

/**
 * 表信息
 */
@Data
public class TableEntity {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表备注
     */
    private String comments;

    /**
     * 驼峰类型
     */
    private String caseClassName;
    /**
     * 普通类型
     */
    private String lowerClassName;

    /**
     * 主键
     */
    private ColumnEntity pk;

    /**
     * 列信息
     */
    private List<ColumnEntity> columns;

}
