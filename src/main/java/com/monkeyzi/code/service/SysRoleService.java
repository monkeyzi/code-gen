package com.monkeyzi.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monkeyzi.code.entity.SysRole;

import java.util.List;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:24
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 查询用户的角色
     * @param userId
     * @return
     */
    List<SysRole> findUserRole(Long userId);
}
