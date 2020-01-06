package com.monkeyzi.code.dto;

import lombok.Data;

@Data
public class GenCodeDto {
    /**
     * 数据源Id
     */
    private Integer id;
    /**
     * 表名
     */
    private String tableName;
}
