package com.lonton.tree.treemenu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色列表项VO类
 *
 * @author 张利红
 */

@Data
public class RoleListItemVO implements Serializable {
    /**
     * 数据id
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
