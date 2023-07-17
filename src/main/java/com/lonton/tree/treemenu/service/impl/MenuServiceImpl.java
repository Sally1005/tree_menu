package com.lonton.tree.treemenu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lonton.tree.treemenu.mapper.TreeMenuMapper;
import com.lonton.tree.treemenu.pojo.entity.TreeMenu;
import com.lonton.tree.treemenu.pojo.vo.MenuSearchVO;
import com.lonton.tree.treemenu.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

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
        loadMenus(menuById, childrenList);
        List<TreeMenu> result = new ArrayList<>();
        result.add(menuById);
        return result;
    }

    @Override
    public List<TreeMenu> searchMenuByName(Long menuId, String menuName) {
        // 创建了一个MenuSearchVO对象
        MenuSearchVO menuSearchVO = new MenuSearchVO();
        // 设置了菜单ID和菜单名称
        menuSearchVO.setMenuName(menuName);
        menuSearchVO.setMenuId(menuId);
        List<TreeMenu> searchMenus = menuMapper.searchMenuByIdAndName(menuSearchVO);
        // 存储所有菜单ID
        Set<Long> allMenuIds = new HashSet<>();
        // 遍历获取所有符合条件的菜单路径,并以"-"分割菜单树路径字符串
        for (TreeMenu searchMenu : searchMenus) {
            String[] split = searchMenu.getMenuTreePath().split("-");
            // 逐个判断分割后的子字符串是否为数字，如果是数字，则将其转换为Long类型,并添加到一个集合中(allMenuIds)
            for (String s : split) {
                if (StringUtils.isNumber(s)) {
                    allMenuIds.add(Long.valueOf(s));
                }
            }
        }
        // 如果没有搜索到符合条件的菜单，此时返回一个空的ArrayList
        if (allMenuIds.size() == 0) {
            return new ArrayList<>();
        }
        // 如果allMenuIds集合不为空，则将其转换为一个menuIdList列表
        List<Long> menuIdList = new ArrayList<>(allMenuIds);
        // 调用menuMapper的findListByMenuIds方法，根据菜单ID列表获取到对应的菜单列表
        List<TreeMenu> menuList = menuMapper.findListByMenuIds(menuIdList);
        // 调用buildTree方法，将菜单列表构建成一个树形结构
        return buildTree(menuList);

    }

    /**
     * 加载菜单
     *
     * @param menuById     菜单
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
