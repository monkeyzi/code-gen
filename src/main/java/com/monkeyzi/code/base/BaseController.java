package com.monkeyzi.code.base;

import com.github.pagehelper.PageInfo;
import com.monkeyzi.code.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseController {

    protected Map<String, Object> getDataTable(PageInfo pageInfo) {
        return getDataTable(pageInfo, CommonConstants.DATA_MAP_INITIAL_CAPACITY);
    }

    protected Map<String, Object> getDataTable(PageInfo pageInfo, int dataMapInitialCapacity) {
        Map<String, Object> data = new HashMap<>(dataMapInitialCapacity);
        data.put("rows", pageInfo.getList());
        data.put("total", pageInfo.getTotal());
        return data;
    }
}
