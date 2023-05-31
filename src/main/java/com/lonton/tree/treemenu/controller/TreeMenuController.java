package com.lonton.tree.treemenu.controller;

import com.lonton.tree.treemenu.common.util.Result;
import com.lonton.tree.treemenu.pojo.entity.TreeMenu;
import com.lonton.tree.treemenu.mapper.TreeMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


/**
 * 树形菜单控制类
 *
 * @author 张利红
 */
@RestController
@RequestMapping("/treeMenu")
public class TreeMenuController {

    @Autowired
    TreeMenuMapper treeMenuMapper;

    /**
     * 获取根节点菜单
     *
     * @return 根节点菜单
     */
    @GetMapping("")
    public Result getRootMenus() {
        List<TreeMenu> menus = treeMenuMapper.getRootMenus();
        for (TreeMenu menu : menus) {
            if (menu != null && menu.getMenuId() != null) {
                Boolean leaf = isLeaf(menu.getMenuId());
                menu.setIsLeaf(leaf);
            }
        }
        return Result.ok().data("menus", menus);
    }

    /**
     * 根据父节点查询子节点数据
     *
     * @param menuId 菜单Id
     * @return 子节点
     */
    @GetMapping("/{menuId}")
    public Result getChildren(@PathVariable Long menuId) {
        // 根据父 menuId 查询所有的子 menu
        List<TreeMenu> menus = treeMenuMapper.getChildren(menuId);
        for (TreeMenu menu : menus) {
            if (menu != null && menu.getMenuId() != null) {
                Boolean leaf = isLeaf(menu.getMenuId());
                menu.setIsLeaf(leaf);
            }
        }
        return Result.ok().data("menus", menus);
    }

    /**
     * 根据名称查询
     *
     * @param menuName 菜单名
     * @return 菜单树
     */
    @GetMapping("/search/{menuName}")
    public Result searchMenus(@PathVariable String menuName) {
        List<TreeMenu> menus = searchItems(menuName);
        return Result.ok().data("menus", menus);
    }

    /**
     * 判断是否是叶子节点
     *
     * @param menuId 菜单Id
     * @return 是否
     */
    public Boolean isLeaf(Long menuId) {
        List<TreeMenu> children = treeMenuMapper.getChildren(menuId);
        return CollectionUtils.isEmpty(children);
    }

    /**
     * 根据菜单名称查询
     *
     * @param menuName 菜单名称
     * @return 菜单树
     */
    public List<TreeMenu> searchItems(String menuName) {
        // 根据关键词从数据库中搜索所有菜单
        List<TreeMenu> allMenus = treeMenuMapper.searchItems(menuName);
        // 创建一个根据 menuId 排序的 TreeSet
        TreeSet<TreeMenu> resultTreeSet = new TreeSet<>(Comparator.comparing(TreeMenu::getMenuId));
        // 创建一个用于存储父菜单 id 的 HashSet
        Set<Long> parentSet = new HashSet<>();
        // 遍历所有菜单
        for (TreeMenu menu : allMenus) {
            // 把菜单加入 TreeSet 中
            resultTreeSet.add(menu);
            // 把菜单的父菜单 id 添加到 parentSet 中
            parentSet.add(menu.getMenuId());
            // 查找菜单的所有父菜单
            Long parentId = menu.getParentMenuId();
            while (parentId != 0L && parentSet.add(parentId)) {
                // 从数据库中获取父菜单
                TreeMenu parentMenu = (TreeMenu) treeMenuMapper.getMenuById(parentId);
                // 如果父菜单不存在，退出循环
                if (parentMenu == null) {
                    break;
                }
                // 把父菜单加入 TreeSet 中
                resultTreeSet.add(parentMenu);
                // 继续查找父菜单的父菜单
                parentId = parentMenu.getParentMenuId();
            }
        }
        // 把 TreeSet 转换成 ArrayList，并返回
        return buildTree(new ArrayList<>(resultTreeSet));
    }


    /**
     * 构建菜单树
     *
     * @param list 菜单列表
     * @return 树结构菜单
     */
    public List<TreeMenu> buildTree(List<TreeMenu> list) {
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
