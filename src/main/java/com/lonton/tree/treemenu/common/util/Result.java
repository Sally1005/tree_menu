package com.lonton.tree.treemenu.common.util;

import lombok.Data;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一结果返回类。方法采用链式调用的写法（即返回类本身 return this）<p/>
 * @author 张利红
 */
@Data
public class Result {
        /**
         * 响应是否成功，true 为成功，false 为失败
         */
        private Boolean success;

        /**
         * 响应状态码， 200 成功，500 系统异常
         */
        private Integer code;

        /**
         * 响应消息
         */
        private String message;

        /**
         * 响应数据
         */
        private Map<String, Object> data = new HashMap<>();

        /**
         * 默认私有构造器
         */
        private Result(){}

        /**
         * 私有自定义构造器<br/>
         * @param success 响应是否成功<br/>
         * @param code 响应状态码<br/>
         * @param message 响应消息
         */
        private Result(Boolean success, Integer code, String message){
            this.success = success;
            this.code = code;
            this.message = message;
        }

        /**
         * 返回一个默认的 成功操作 的结果，默认响应状态码 200<br/>
         * @return 成功操作的实例对象
         */
        public static Result ok() {
            return new Result(true, HttpStatus.SC_OK, "success");
        }

        /**
         * 返回一个自定义 成功操作 的结果<br/>
         * @param success 响应是否成功<br/>
         * @param code 响应状态码<br/>
         * @param message 响应消息<br/>
         * @return 成功操作的实例对象
         */
        public static Result ok(Boolean success, Integer code, String message) {
            return new Result(success, code, message);
        }

        /**
         * 返回一个默认的 失败操作 的结果，默认响应状态码为 500<br/>
         * @return 失败操作的实例对象
         */
        public static Result error() {
            return new Result(false, HttpStatus.SC_INTERNAL_SERVER_ERROR, "error");
        }

        /**
         * 返回一个自定义 失败操作 的结果<br/>
         * @param success 响应是否成功<br/>
         * @param code 响应状态码<br/>
         * @param message 相应消息<br/>
         * @return 失败操作的实例对象
         */
        public static Result error(Boolean success, Integer code, String message) {
            return new Result(success, code, message);
        }

        /**
         * 自定义响应是否成功<br/>
         * @param success 响应是否成功<br/>
         * @return 当前实例对象
         */
        public Result success(Boolean success) {
            this.setSuccess(success);
            return this;
        }

        /**
         * 自定义响应状态码<br/>
         * @param code 响应状态码<br/>
         * @return 当前实例对象
         */
        public Result code(Integer code) {
            this.setCode(code);
            return this;
        }

        /**
         * 自定义响应消息<br/>
         * @param message 响应消息<br/>
         * @return 当前实例对象
         */
        public Result message(String message) {
            this.setMessage(message);
            return this;
        }

        /**
         * 自定义响应数据，一次设置一个 map 集合<br/>
         * @param map 响应数据<br/>
         * @return 当前实例对象
         */
        public Result data(Map<String, Object> map) {
            this.data.putAll(map);
            return this;
        }

        /**
         * 通用设置响应数据，一次设置一个 key - value 键值对<br/>
         * @param key 键<br/>
         * @param value 数据<br/>
         * @return 当前实例对象
         */
        public Result data(String key, Object value) {
            this.data.put(key, value);
            return this;
        }
    }
