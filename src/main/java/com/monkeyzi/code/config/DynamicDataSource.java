package com.monkeyzi.code.config;

import com.monkeyzi.code.holder.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("当前数据源为=="+DynamicDataSourceContextHolder.getDataSourceType());
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
