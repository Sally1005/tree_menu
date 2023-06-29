package com.lonton.tree.treemenu.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**
 * 用户登录VO类
 *
 * @author 张利红
 */
@Data
public class UserLoginInfoVO implements Serializable {
    /**
     * 数据id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码（密文）
     */
    private String password;

    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;

    /**
     * 用户的权限列表
     */
    private List<String> permissions;

}
