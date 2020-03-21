package com.monkeyzi.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monkeyzi.code.entity.SysUser;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:01
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectUserByUserName(String userName);
}
