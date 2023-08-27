package com.caicai.entity.userReadNewsRecord;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.caicai.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("user_read_news_record")
public class UserReadNewsRecord extends BaseEntity {

    @TableId
    private String id;
    /**
     * 新闻类型
     */
    @TableField("subscribe")
    private String subscribe;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;
    /**
     * 标题1
     */
    @TableField("title1")
    private String title1;


}
