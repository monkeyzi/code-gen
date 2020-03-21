package com.monkeyzi.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monkeyzi.code.entity.SysRole;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:23
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectUserRole(Long userId);
}
