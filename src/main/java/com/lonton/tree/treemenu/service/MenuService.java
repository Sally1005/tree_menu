package com.lonton.tree.treemenu.service;

import com.lonton.tree.treemenu.pojo.entity.TreeMenu;

import java.util.List;

/**
 * 菜单接口
 *
 * @author 张利红
 */
public interface MenuService {

    /**
     * 根据用户Id获取用户的树形菜单列表
     *
     * @param userId 用户Id
     * @return 树结构菜单
     */
    List<TreeMenu> findTreeMenuListByUserId(Long userId);

    /**
     * 根据菜单Id获取树形菜单列表
     *
     * @param menuId 菜单Id
     * @return 树结构菜单
     */
    List<TreeMenu> getAllMenuByMenuId(Long menuId);

    /**
     * 根据菜单Id和菜单名称查询菜单
     *
     * @param menuId  菜单Id
     * @param menuName 菜单名称
     * @return 菜单树
     */
    List<TreeMenu> searchMenuByName(Long menuId, String menuName);

    /**
     * 根据菜单Id查询菜单路径
     *
     * @param menuId 菜单Id
     * @return 菜单树
     */
    List<TreeMenu> queryPath(Long menuId);
}
