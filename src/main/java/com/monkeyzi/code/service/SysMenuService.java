package com.monkeyzi.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monkeyzi.code.dto.MenuTree;
import com.monkeyzi.code.entity.SysMenu;
import com.monkeyzi.code.entity.SysRole;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:24
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 查询用户的权限
     * @param userId
     * @return
     */
    List<SysMenu> findUserPermissions(Long userId);

    /**
     * 导航菜单
     * @param id
     * @return
     */
    MenuTree<SysMenu> findUserMenus(Long id);
}
