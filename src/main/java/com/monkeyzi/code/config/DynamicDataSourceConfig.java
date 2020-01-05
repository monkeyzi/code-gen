package com.monkeyzi.code.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.monkeyzi.code.constant.DataSourceConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Configuration
public class DynamicDataSourceConfig  implements TransactionManagementConfigurer {

    private final Map<Object, Object> dataSourceMap = new HashMap<>(8);
    private final DruidDataSourceProperties druidDataSourceProperties;

    public DynamicDataSourceConfig(DruidDataSourceProperties druidDataSourceProperties) {
        this.druidDataSourceProperties = druidDataSourceProperties;
    }


    @Bean("dynamicDataSource")
    public DynamicDataSource dataSource(){
        DynamicDataSource ds=new DynamicDataSource();
        DruidDataSource  dds=new DruidDataSource();
        dds.setUrl(druidDataSourceProperties.getUrl());
        dds.setDriverClassName(druidDataSourceProperties.getDriverClassName());
        dds.setUsername(druidDataSourceProperties.getUsername());
        dds.setPassword(druidDataSourceProperties.getPassword());
        ds.setDefaultTargetDataSource(dds);
        dataSourceMap.put(0, dds);
        ds.setTargetDataSources(dataSourceMap);
        return ds;
    }


    @PostConstruct
    public void init() {
        DriverManagerDataSource dds = new DriverManagerDataSource();
        dds.setUrl(druidDataSourceProperties.getUrl());
        dds.setDriverClassName(druidDataSourceProperties.getDriverClassName());
        dds.setUsername(druidDataSourceProperties.getUsername());
        dds.setPassword(druidDataSourceProperties.getPassword());
        List<Map<String, Object>> dbList = new JdbcTemplate(dds).queryForList(DataSourceConstants.QUERY_DS_SQL);
        log.info("开始 -> 初始化动态数据源");
        Optional.of(dbList).ifPresent(list -> list.forEach(db -> {
            log.info("数据源:{}", db.get(DataSourceConstants.DS_NAME));
            DruidDataSource ds = new DruidDataSource();
            ds.setUrl(String.valueOf(db.get(DataSourceConstants.DS_JDBC_URL)));
            ds.setDriverClassName(String.valueOf(db.get(DataSourceConstants.DS_DRIVER_CLASS_NAME)));
            ds.setUsername((String) db.get(DataSourceConstants.DS_USER_NAME));

            String decPwd = ((String) db.get(DataSourceConstants.DS_USER_PWD));
            ds.setPassword(decPwd);
            if (dataSourceMap.containsKey(db.get(DataSourceConstants.DS_ROUTE_KEY))){
                dataSourceMap.remove(db.get(DataSourceConstants.DS_ROUTE_KEY));
            }
            dataSourceMap.put(db.get(DataSourceConstants.DS_ROUTE_KEY), ds);
        }));

        log.info("完毕 -> 初始化动态数据源,共计 {} 条", dataSourceMap.size());

    }


    /**
     * 重新加载数据源配置
     */
    public Boolean reload() {
        init();
        DynamicDataSource dataSource = dataSource();
        dataSource.setTargetDataSources(dataSourceMap);
        dataSource.afterPropertiesSet();
        return Boolean.FALSE;
    }


    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }
}
