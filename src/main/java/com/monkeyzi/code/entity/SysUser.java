package com.monkeyzi.code.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author: 高yg
 * @date: 2020/1/4 17:30
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:系统用户表
 */
@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends Model<SysUser> {

    @TableId
    private Long id;
    private String  userName;
    private String  password;
    private LocalDateTime lastLoginTime;
    private String  status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String avatar;
    private String theme;
    private String isTab;
    @TableField(exist = false)
    private String roleId;
    @TableField(exist = false)
    private String roleName;

}
