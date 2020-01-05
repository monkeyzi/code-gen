package com.monkeyzi.code.constant;

/**
 * 系统常量
 */
public interface CommonConstants {
    /**
     * 默认排序字段
     */
    String ORDER_BY_FIELD="CREATE_TIME";
    /**
     * 排序顺序
     */
    String ORDER_BY="desc";
    /**
     * map初始容量
     */
    int DATA_MAP_INITIAL_CAPACITY = 4;

    /**
     *  前端页面路径前缀
      */
    String VIEW_PREFIX = "febs/views/";
    /**
     * 可用状态
     */
    String ENABLED_STATUS="0";
    /**
     * 禁用状态
     */
    String DISABLED_STATUS="1";

    String CODE_PREFIX = "monx_captcha_";
}
