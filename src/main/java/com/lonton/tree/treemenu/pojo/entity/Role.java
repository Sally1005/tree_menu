package com.lonton.tree.treemenu.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色类
 *
 * @author 张利红
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

}
