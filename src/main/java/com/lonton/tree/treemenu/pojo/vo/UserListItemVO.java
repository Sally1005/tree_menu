package com.lonton.tree.treemenu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户列表项VO类
 *
 * @author 张利红
 */
@Data
public class UserListItemVO implements Serializable {

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
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;

}
