package com.monkeyzi.code.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:05
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@TableName("sys_menu")
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends Model<SysMenu> {

    /**
     * 菜单/按钮ID
     */
    @TableId
    private Long id;

    /**
     * 上级菜单ID
     */
    private Long parentId;


    private String menuName;


    private String url;


    private String perms;


    private String icon;

    private String type;

    private Long orderNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
