package com.monkeyzi.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.monkeyzi.code.entity.SysMenu;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:24
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findUserPermissions(Long userId);

    List<SysMenu> findUserMenus(Long id);
}
