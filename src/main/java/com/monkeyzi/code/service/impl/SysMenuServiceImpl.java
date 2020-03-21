package com.monkeyzi.code.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.monkeyzi.code.dto.MenuTree;
import com.monkeyzi.code.entity.SysMenu;
import com.monkeyzi.code.entity.SysRole;
import com.monkeyzi.code.mapper.SysMenuMapper;
import com.monkeyzi.code.mapper.SysRoleMapper;
import com.monkeyzi.code.service.SysMenuService;
import com.monkeyzi.code.service.SysRoleService;
import com.monkeyzi.code.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:25
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu> implements SysMenuService {
    @Override
    public List<SysMenu> findUserPermissions(Long userId) {
        return this.baseMapper.findUserPermissions(userId);
    }

    @Override
    public MenuTree<SysMenu> findUserMenus(Long id) {
        List<SysMenu> menus = this.baseMapper.findUserMenus(id);
        List<MenuTree<SysMenu>> trees = this.convertMenus(menus);
        return TreeUtil.buildMenuTree(trees);
    }


    private List<MenuTree<SysMenu>> convertMenus(List<SysMenu> menus) {
        List<MenuTree<SysMenu>> trees = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree<SysMenu> tree = new MenuTree<>();
            tree.setId(String.valueOf(menu.getId()));
            tree.setParentId(String.valueOf(menu.getParentId()));
            tree.setTitle(menu.getMenuName());
            tree.setIcon(menu.getIcon());
            tree.setHref(menu.getUrl());
            tree.setData(menu);
            trees.add(tree);
        });
        return trees;
    }
}
