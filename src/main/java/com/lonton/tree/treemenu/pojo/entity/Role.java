package com.lonton.tree.treemenu.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色的实体类
 * @author 张利红
 */
@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;
}
