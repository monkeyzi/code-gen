package com.monkeyzi.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.monkeyzi.code.dto.PageRequest;
import com.monkeyzi.code.entity.SysDatasourceConf;

public interface DataSourceConfService extends IService<SysDatasourceConf> {
    /**
     * 分页查询数据源信息
     * @param page
     * @param sysDatasourceConf
     * @return
     */
    PageInfo pageQuery(PageRequest page, SysDatasourceConf sysDatasourceConf);

    /**
     * 新增数据源
     * @param sysDatasourceConf
     */
    void saveDataSource(SysDatasourceConf sysDatasourceConf);

    /**
     * 数据源更新成功
     * @param sysDatasourceConf
     */
    void updateDataSource(SysDatasourceConf sysDatasourceConf);

    void deleteDataSource(Long id);
}
