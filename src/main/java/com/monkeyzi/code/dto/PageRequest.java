package com.monkeyzi.code.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class PageRequest implements Serializable {
    private static final long serialVersionUID = -4869594085374385813L;
    // 当前页面数据量
    private int pageSize;
    // 当前页码
    private int pageNum;
    // 排序字段
    private String field;
    // 排序规则，asc升序，desc降序
    private String order="desc";

}
