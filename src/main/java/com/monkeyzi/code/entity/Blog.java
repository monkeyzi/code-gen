package com.monkeyzi.code.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("blog_content")
public class Blog {

    @TableId
    private Integer blogId;

    private String blogTitle;

}
