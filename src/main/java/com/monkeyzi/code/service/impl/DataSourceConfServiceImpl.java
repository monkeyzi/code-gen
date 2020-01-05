package com.monkeyzi.code.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monkeyzi.code.config.DynamicDataSourceConfig;
import com.monkeyzi.code.constant.CommonConstants;
import com.monkeyzi.code.dto.PageRequest;
import com.monkeyzi.code.entity.SysDatasourceConf;
import com.monkeyzi.code.mapper.DataSourceConfMapper;
import com.monkeyzi.code.service.DataSourceConfService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DataSourceConfServiceImpl extends ServiceImpl<DataSourceConfMapper, SysDatasourceConf> implements DataSourceConfService {
    @Autowired
    private DynamicDataSourceConfig dataSourceConfig;

    @Override
    public PageInfo pageQuery(PageRequest page, SysDatasourceConf sysDatasourceConf) {
        PageHelper.startPage(page.getPageNum(),page.getPageSize())
                .setOrderBy(CommonConstants.ORDER_BY_FIELD+" "+CommonConstants.ORDER_BY);
        QueryWrapper<SysDatasourceConf> queryWrapper=new QueryWrapper();
        if (StrUtil.isNotBlank(sysDatasourceConf.getDbType())){
            queryWrapper.lambda().eq(SysDatasourceConf::getDbType,sysDatasourceConf.getDbType());
        }
        if (StringUtils.isNotBlank(sysDatasourceConf.getName())) {
            queryWrapper.lambda().like(SysDatasourceConf::getName,sysDatasourceConf.getName());
        }
        List<SysDatasourceConf> list=this.baseMapper.selectList(queryWrapper);
        return new PageInfo(list);
    }

    @Override
    public void saveDataSource(SysDatasourceConf sysDatasourceConf) {
        //将数据库密码加密 TODO
        sysDatasourceConf.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(sysDatasourceConf);
        dataSourceConfig.reload();
    }

    @Override
    public void updateDataSource(SysDatasourceConf sysDatasourceConf) {
        sysDatasourceConf.setUpdateTime(LocalDateTime.now());
        this.baseMapper.updateById(sysDatasourceConf);
        dataSourceConfig.reload();
    }

    @Override
    public void deleteDataSource(Long id) {
        this.baseMapper.deleteById(id);
        dataSourceConfig.reload();
    }


}
