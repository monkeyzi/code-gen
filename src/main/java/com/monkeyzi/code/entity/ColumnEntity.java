package com.monkeyzi.code.entity;

import lombok.Data;

/**
 * 数据库列属性
 */
@Data
public class ColumnEntity {
    /**
     * 列名
     */
    private String  columnName;
    /**
     * 数据类型
     */
    private String  dataType;
    /**
     * 备注
     */
    private String  comments;
    /**
     * 驼峰属性
     */
    private String  caseAttrName;
    /**
     * 普通属性
     */
    private String  lowerAttrName;
    /**
     * 属性类型
     */
    private String  attrType;
    /**
     * 其他属性
     */
    private String  extra;
}
