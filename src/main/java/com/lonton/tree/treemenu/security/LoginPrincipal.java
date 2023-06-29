package com.lonton.tree.treemenu.security;

import lombok.Data;

import java.io.Serializable;
/**
 * 当前登录用户
 *
 * @author 张利红
 */
@Data
public class LoginPrincipal implements Serializable {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

}
