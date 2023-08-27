package com.caicai.common;

import lombok.Data;

/**
 * 需要分页的模型
 *
 */
@Data
public class PaginationVO {
    private Long currentPage;
    private Long pageSize;
    private Integer total;
}
