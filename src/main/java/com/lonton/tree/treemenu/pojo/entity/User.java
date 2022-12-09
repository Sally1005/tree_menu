package com.lonton.tree.treemenu.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户的实体类
 * @author 张利红
 */
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;
}
