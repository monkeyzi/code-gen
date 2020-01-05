package com.monkeyzi.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monkeyzi.code.entity.SysRole;
import com.monkeyzi.code.mapper.SysRoleMapper;
import com.monkeyzi.code.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:25
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {
    @Override
    public List<SysRole> findUserRole(Long userId) {
        return baseMapper.selectUserRole(userId);
    }
}
