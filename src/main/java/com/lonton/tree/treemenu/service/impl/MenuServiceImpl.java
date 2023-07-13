package com.lonton.tree.treemenu.service.impl;

import com.lonton.tree.treemenu.mapper.TreeMenuMapper;
import com.lonton.tree.treemenu.pojo.entity.TreeMenu;
import com.lonton.tree.treemenu.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单实现类
 *
 * @author 张利红
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private TreeMenuMapper menuMapper;

    @Override
    public List<TreeMenu> findTreeMenuListByUserId(Long userId) {
        // 1. 查询当前用户角色关联的菜单
        List<TreeMenu> menuList = menuMapper.findListByUserId(userId);
        // 2. 封装成树形结构
        return this.buildTree(menuList);
    }

    @Override
    public List<TreeMenu> getAllMenuByMenuId(Long menuId) {
        // 根据菜单ID获取菜单
        TreeMenu menuById = menuMapper.getMenuById(menuId);
        // 获取子菜单列表
        List<TreeMenu> childrenList = menuMapper.getChildren(menuId);
        // 加载菜单
        loadMenus(menuById,childrenList);
        List<TreeMenu> result = new ArrayList<>();
        result.add(menuById);
        return result;
    }

    /**
     * 加载菜单
     *
     * @param menuById 菜单
     * @param childrenList 子菜单列表
     */
    private void loadMenus(TreeMenu menuById, List<TreeMenu> childrenList) {
        // 设置子菜单列表
        menuById.setChildren(childrenList);
        for (TreeMenu treeMenu : childrenList) {
            // 获取子菜单的子菜单列表
            List<TreeMenu> children = menuMapper.getChildren(treeMenu.getMenuId());
            // 如果子菜单列表为空，则结束递归
            if (children.isEmpty()) {
                return;
            }
            // 递归加载子菜单
            loadMenus(treeMenu, children);
        }
    }

    /**
     * 构建菜单树
     *
     * @param list 菜单列表
     * @return 树结构菜单
     */
    private List<TreeMenu> buildTree(List<TreeMenu> list) {
        List<TreeMenu> root = new ArrayList<>();
        // 有序排列
        Map<Long, TreeMenu> menuMap = new LinkedHashMap<>();
        // 遍历列表
        for (TreeMenu menu : list) {
            // 将菜单添加到menuMap中
            menuMap.put(menu.getMenuId(), menu);
            // 检查是否为根节点
            if (menu.getParentMenuId() == 0L) {
                root.add(menu);
            } else {
                // 获取父菜单并将当前菜单添加到其子菜单中
                TreeMenu parentMenu = menuMap.get(menu.getParentMenuId());
                if (parentMenu != null) {
                    parentMenu.getChildren().add(menu);
                }
            }
        }
        // 返回根节点
        return root;
    }
}
