package com.lonton.tree.treemenu.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * (TreeMenu)实体类
 * <p/>
 * @author 张利红
 */
@Data
public class TreeMenu implements Serializable {
    private static final long serialVersionUID = -27923712765212107L;
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
    private Integer menuLevel;
    /**
     * 排序
     */
    private Integer menuSort;
    /**
     * 用于保存一个菜单的子菜单
     */
    @TableField(exist = false)
    private List<TreeMenu> children;
    /**
     * 判断是否为叶子节点
     */
    @TableField(exist = false)
    private Boolean isLeaf = false;

}



