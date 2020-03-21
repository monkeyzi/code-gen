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
 * @date: 2020/1/2 22:41
 * @qq:854152531@qq.com
 * @blog http://www.gaoyanguo.com
 * @description:
 */
@Data
@TableName("sys_gen_config")
@EqualsAndHashCode(callSuper = true)
public class SysGenConfig extends Model<SysGenConfig> {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String author;

    private String packageName;

    private String moduleName;

    private String projectName;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField(exist = false)
    private Integer dsId;

    @TableField(exist = false)
    private String tableName;
}
