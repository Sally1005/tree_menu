package com.lonton.tree.treemenu.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色类
 *
 * @author 张利红
 */
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

}
