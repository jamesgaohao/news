package com.caicai.entity.subcribetype;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.caicai.entity.BaseEntity;
import lombok.Data;


@Data
@TableName("subscribe_type")
public class SubscribeType extends BaseEntity {
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 订阅新闻类型名称
     */
    @TableField("subscribe_name")
    private String subscribeName;


    /**
     * 订阅新闻类型名称
     */
    @TableField("url")
    private String url;

}
