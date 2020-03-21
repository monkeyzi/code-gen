package com.monkeyzi.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.monkeyzi.code.entity.SysUser;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:04
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
public interface SysUserService  extends IService<SysUser> {
    /**
     * 查询用户信息
     * @param userName
     * @return
     */
    SysUser findUserByUserName(String userName);
}
