package com.monkeyzi.code.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@TableName("sys_datasource_conf")
@EqualsAndHashCode(callSuper = true)
public class SysDatasourceConf extends Model<SysDatasourceConf> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * jdbc url
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新
     */
    private LocalDateTime updateTime;
    /**
     * 删除标记
     */
    @TableLogic
    private Integer delFlag;
    /**
     * driver
     */
    private String driverClassName;
    /**
     * 数据库类型
     */
    private String dbType;


}
