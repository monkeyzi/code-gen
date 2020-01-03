package com.monkeyzi.code.dto;

import lombok.Data;

/**
 * @author: 高yg
 * @date: 2020/1/2 23:04
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
public class GenConfigDto {
    /**
     * 数据源Id
     */
    private Integer id;

    private String projectName;
    /**
     * 作者
     */
    private String author;
    /**
     * 基础包名
     */
    private String packageName;
    /**
     * 模块名
     */
    private String moduleName;
    /**
     * 是否去除表前缀
     */
    private Integer isTrim;
    /**
     * 表前缀
     */
    private String  tablePrefix;
    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表备注
     */
    private String comments;


}
