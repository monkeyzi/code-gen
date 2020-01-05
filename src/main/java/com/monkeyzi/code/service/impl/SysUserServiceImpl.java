package com.monkeyzi.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monkeyzi.code.entity.SysUser;
import com.monkeyzi.code.mapper.SysUserMapper;
import com.monkeyzi.code.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:05
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {


    @Override
    public SysUser findUserByUserName(String userName) {
        return this.baseMapper.selectUserByUserName(userName);
    }
}
