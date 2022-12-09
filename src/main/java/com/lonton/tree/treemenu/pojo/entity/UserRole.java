package com.lonton.tree.treemenu.pojo.entity;

import lombok.Data;

import java.io.Serializable;
/**
 * 用户与角色的关联数据的实体类
 * @author 张利红
 */
@Data
public class UserRole implements Serializable {
    /**
     * 数据id
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
