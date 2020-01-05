package com.monkeyzi.code.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author: 高yg
 * @date: 2020/1/4 18:08
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Data
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {

    /**
     * 角色ID
     */
    @TableId
    private Long id;


    private String roleName;


    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    private Integer status;
}
