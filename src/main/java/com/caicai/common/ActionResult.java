package com.caicai.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public static ActionResult success() {
        ActionResult jsonData = new ActionResult();
        jsonData.setCode(200);
        jsonData.setMsg("Success");
        return jsonData;
    }

    public static ActionResult success(String msg) {
        ActionResult jsonData = new ActionResult();
        jsonData.setCode(200);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public static ActionResult success(Object object) {
        ActionResult jsonData = new ActionResult();
        jsonData.setData(object);
        jsonData.setCode(200);
        jsonData.setMsg("Success");
        return jsonData;
    }
    public static<T> ActionResult page(List<T> list, PaginationVO pagination) {
        ActionResult jsonData = new ActionResult();
        PageListVO<T> vo = new PageListVO<>();
        vo.setList(list);
        vo.setPagination(pagination);
        jsonData.setData(vo);
        jsonData.setCode(200);
        jsonData.setMsg("Success");
        return jsonData;
    }



    public static ActionResult success(String msg, Object object) {
        ActionResult jsonData = new ActionResult();
        jsonData.setData(object);
        jsonData.setCode(200);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public static ActionResult fail(Integer code, String message) {
        ActionResult jsonData = new ActionResult();
        jsonData.setCode(code);
        jsonData.setMsg(message);
        return jsonData;
    }

    public static ActionResult fail(Integer code, String message, Object object) {
        ActionResult jsonData = new ActionResult();
        jsonData.setCode(code);
        jsonData.setMsg(message);
        jsonData.setData(object);
        return jsonData;
    }

    public static ActionResult fail(String msg, String data) {
        ActionResult jsonData = new ActionResult();
        jsonData.setMsg(msg);
        jsonData.setData(data);
        return jsonData;
    }

    public static ActionResult fail(String msg) {
        ActionResult jsonData = new ActionResult();
        jsonData.setMsg(msg);
        jsonData.setCode(400);
        return jsonData;
    }
}
