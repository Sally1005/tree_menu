package com.lonton.tree.treemenu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户标准VO类
 *
 * @author 张利红
 */
@Data
public class UserStandardVO implements Serializable {
    /**
     * 数据id
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
     * 密码
     */
    private String password;

    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;

}
