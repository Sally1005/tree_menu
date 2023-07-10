package com.lonton.tree.treemenu.web;

import com.lonton.tree.treemenu.common.exception.GlobalException;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一的响应结果类型
 */
@Data
public class JsonResult<T> implements Serializable {

    /**
     * 业务状态码
     */
    private Integer state;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public static JsonResult ok() {
        return ok(null);
    }

    public static <T> JsonResult<T> ok(T data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(ServiceCode.OK);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult<Void> fail(GlobalException e) {
        return fail(e.getCode(), e.getMessage());
    }
    public static <T> JsonResult<T> error(Integer state, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(state);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static JsonResult<Void> fail(Integer state, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(state);
        jsonResult.setMessage(message);
        return jsonResult;
    }

}
