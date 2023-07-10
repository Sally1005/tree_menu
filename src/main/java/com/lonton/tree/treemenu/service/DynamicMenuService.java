package com.lonton.tree.treemenu.service;

import com.lonton.tree.treemenu.pojo.entity.TreeDynamicMenu;

import java.util.List;

/**
 * 动态菜单接口
 *
 * @author 张利红
 */
public interface DynamicMenuService {
    /**
     * 根据用户Id获取用户的树形菜单列表
     *
     * @param userId 用户Id
     * @return 树结构菜单
     */
    List<TreeDynamicMenu> findTreeMenuListByUserId(Long userId);
}
