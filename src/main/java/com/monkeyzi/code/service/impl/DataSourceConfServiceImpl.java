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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<SysDatasourceConf> list=this.baseMapper.selectList(queryWrapper);
        return new PageInfo(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDataSource(SysDatasourceConf sysDatasourceConf) {
        //将数据库密码加密 TODO
        this.baseMapper.insert(sysDatasourceConf);
        dataSourceConfig.reload();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDataSource(SysDatasourceConf sysDatasourceConf) {
        this.baseMapper.updateById(sysDatasourceConf);
        dataSourceConfig.reload();
    }


}
