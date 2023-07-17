package com.lonton.tree.treemenu.pojo.vo;

import lombok.Data;

/**
 * 菜单查询VO类
 *
 * @author 张利红
 */
@Data
public class MenuSearchVO {
    /**
     * 菜单Id
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;
}
