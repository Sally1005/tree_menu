package com.lonton.tree.treemenu.pojo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 树形菜单实体类
 *
 * @author 张利红
 */
@Data
@Accessors(chain = true)
public class TreeMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前菜单ID
     */
    private Long menuId;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 当前菜单的父菜单ID
     */
    private Long parentMenuId;
    /**
     * 当前菜单的层级
     */
    private Long menuLevel;

    /**
     * 用于保存一个菜单的子菜单
     */
    private List<TreeMenu> children;
    /**
     * 判断是否为叶子节点
     */
    private Boolean isLeaf = false;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private Integer isDelete;

    /**
     * 菜单路径
     */
    private String menuTreePath;

    /**
     * 无参构造
     */
    public TreeMenu() {
        this.children = new ArrayList<>();
    }

    /**
     * 获取子菜单
     *
     * @return 子菜单
     */
    public List<TreeMenu> getChildren() {
        return children;
    }


}
