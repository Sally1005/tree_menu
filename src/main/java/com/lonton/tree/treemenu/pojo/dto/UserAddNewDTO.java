package com.lonton.tree.treemenu.pojo.dto;

import lombok.Data;

import java.io.Serializable;
/**
 * 新增用户DTO
 *
 * @author 张利红
 */
@Data
public class UserAddNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户关联到的若干个角色的id
     */
    private  Long[] roleIds;

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
}
