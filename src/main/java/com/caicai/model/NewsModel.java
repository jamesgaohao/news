package com.caicai.model;

import com.caicai.common.Pagination;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NewsModel  extends Pagination {
    /**
     * 用户ID
     */
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userName")
    private String userName;


}
