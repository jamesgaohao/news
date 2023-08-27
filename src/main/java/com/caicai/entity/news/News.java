package com.caicai.entity.news;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.caicai.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
@TableName("news")
public class News extends BaseEntity {
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 标题1
     */
    @TableField("title1")
    private String title1;
    /**
     * 标题2
     */
    @TableField("title2")
    private String title2;
    /**
     * 标题3
     */
    @TableField("title3")
    private String title3;
    /**
     * 发布人
     */
    @TableField("publisher")
    private String publisher;
    /**
     * 新闻内容
     */
    @TableField("content")
    private String content;
    /**
     * 新闻类型
     */
    @TableField("subscribe_id")
    private String subscribeId;

    /**
     * 新闻发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @TableField("push_time")
    private Date pushTime;

}
