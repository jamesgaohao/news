package com.caicai.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;


@Data
public class Pagination extends Page {
    private long pageSize=9;
    private String sort="desc";
    private String sidx="";
    private long currentPage=1;

    @JsonIgnore
    private long total;
    @JsonIgnore
    private long records;

    public <T> List<T> setData(List<T> data, long records) {
        this.total = records;
        return data;
    }
}
