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

    List<TreeMenu> getAllMenuByMenuId(Long menuId);
}
