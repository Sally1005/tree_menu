package com.lonton.tree.treemenu.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录DTO
 *
 * @author 张利红
 */
@Data
public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;


    /**
     * 密码（密文）
     */
    private String password;
}
