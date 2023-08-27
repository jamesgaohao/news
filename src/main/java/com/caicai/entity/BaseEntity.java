package com.caicai.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;


@Data
public class BaseEntity {

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 删除标识 0-false 1- true
     */
    @TableField("delete_flg")
    private Boolean deleteFlg;

}
