package com.lonton.tree.treemenu.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

}
