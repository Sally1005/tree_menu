package com.lonton.tree.treemenu.pojo.entity;

import lombok.Data;
import lombok.experimental.Accessors;


import java.io.Serializable;


/**
 * 用户类
 *
 * @author 张利红
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码（密文）
     */
    private String password;

    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;
}
