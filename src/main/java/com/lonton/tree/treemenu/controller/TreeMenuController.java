package com.lonton.tree.treemenu.controller;

import com.lonton.tree.treemenu.common.util.Result;
import com.lonton.tree.treemenu.pojo.TreeMenu;
import com.lonton.tree.treemenu.mapper.TreeMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;


/**
 * (TreeMenu)表控制层
 * <p/>
 *
 * @author 张利红
 */
@RestController
@RequestMapping(value = {"treeMenu", "lazyTreeMenu"})
public class TreeMenuController {

    @Autowired

    TreeMenuMapper treeMenuMapper;

    /**
     * 获取根节点菜单 <br/>
     *
     * @return 根节点菜单
     */
    @GetMapping("")
    public Result getRootMenus() {
        List<TreeMenu> menus = treeMenuMapper.getRootMenus();
        for (TreeMenu menu : menus) {
            Boolean leaf = isLeaf(menu.getMenuId());
            menu.setIsLeaf(leaf);
        }
        return Result.ok().data("menus", menus);
    }

    /**
     * 根据父节点查询子节点数据 <br/>
     *
     * @param menuId <br/>
     * @return 子节点
     */
    @GetMapping("/{menuId}")
    public Result getChildren(@PathVariable Long menuId) {
        // 根据父 menuId 查询所有的子 menu
        List<TreeMenu> menus = treeMenuMapper.getChildren(menuId);
        for (TreeMenu menu : menus) {
            Boolean leaf = isLeaf(menu.getMenuId());
            menu.setIsLeaf(leaf);
        }
        return Result.ok().data("menus", menus);
    }

    /**
     * 根据名称查询 <br/>
     *
     * @param menuName <br/>
     * @return 菜单树
     */
    @GetMapping("/search/{menuName}")
    public Result searchMenus(@PathVariable String menuName) {
        List<TreeMenu> menus = searchItems(menuName);
        return Result.ok().data("menus", menus);
    }

    /**
     * 判断是否是叶子节点 <br/>
     *
     * @param menuId <br/>
     * @return 是否
     */
    public Boolean isLeaf(Long menuId) {
        List<TreeMenu> children = treeMenuMapper.getChildren(menuId);
        if (CollectionUtils.isEmpty(children)) {
            return true;
        }
        return false;
    }

    /**
     * 搜素查询 <br/>
     * 相当于模糊查询。与之前在xml里实现的模糊查询相比，
     * 不单单查的是包含关键字的字段，它能将包含关键字的
     * 父节点也返回，能够以一棵树的形式返回
     *
     * @param name 菜单名
     * @return 菜单树
     */
    // 根据包含的菜单名称关键字，类似于模糊查询，将包含关键字的菜单以一棵树返回
    public List<TreeMenu> searchItems(String name) {
//        // 结果集
//        List<TreeMenu> resultTreeSet = new ArrayList<>();
//        Set<TreeMenu> parentSet = new HashSet<>();
//        Set<TreeMenu> rootSet = new HashSet<>();
//
//        List<TreeMenu> menuList = treeMenuMapper.searchItems(name);
//        for (TreeMenu menu : menuList) {
//// 如果该菜单已经在结果集中出现过，则跳过本次循环
//            if (resultTreeSet.contains(menu)) {
//                continue;
//            }
//
//// 遍历该菜单和其所有父级菜单，并将它们加入结果集和 parentSet
//            TreeMenu current = menu;
//            while (current.getParentMenuId() != null) {
//// 如果该菜单还有父级菜单，则继续向上查找
//                boolean foundParent = false;
//// 遍历所有菜单项，找到当前菜单项的父级菜单
//                for (TreeMenu parent : menuList) {
//                    if (current.getParentMenuId().equals(parent.getMenuId())) {
//                        resultTreeSet.add(parent);
//                        parentSet.add(parent);
//                        current = parent;
//                        foundParent = true;
//                        break;
//                    }
//                }
//                if (!foundParent) { // 如果没有找到当前菜单项的父级菜单，则跳出循环
//                    break;
//                }
//            }
//
//// 将菜单项本身加入结果集和 parentSet
//            resultTreeSet.add(menu);
//            parentSet.add(menu);
//        }
//
//// 遍历 resultTreeSet 集合，将没有父节点的节点作为根节点提取出来
//        for (TreeMenu menu : resultTreeSet) {
//            if (menu.getParentMenuId() == null) { // 如果当前菜单项没有父节点，则将它作为根节点
//                rootSet.add(menu);
//            }
//        }
//
//        return buildTree(resultTreeSet);
//    }
        List<TreeMenu> allMenus = treeMenuMapper.searchItems(name);
        Map<Long, TreeMenu> menuMap = new HashMap<>();
        for (TreeMenu menu : allMenus) {
            menuMap.put(menu.getMenuId(), menu);
        }
        // 再在查询出所有的数据中进行搜索过滤
        List<TreeMenu> menusAfterSearched = new ArrayList<>();
        for (TreeMenu menu : allMenus) {
            if (menu.getMenuName().contains(name)) {
                if (!menusAfterSearched.contains(menu)) {
                    menusAfterSearched.add(menu);
                }
                // 父节点
                Long parentId = menu.getParentMenuId();
                while (parentId != 0) {
                    TreeMenu treeMenu = menuMap.get(parentId);
                    if (!menusAfterSearched.contains(treeMenu)) {
                        menusAfterSearched.add(treeMenu);
                    }
                    parentId = treeMenu.getParentMenuId();
                }
            }
        }
        return buildTree();
    }


    /**
     * 构建树形菜单
     *
     * @return 树形菜单
     */
    public List<TreeMenu> buildTree() {
        List<TreeMenu> menus = treeMenuMapper.buildTree();
        List<TreeMenu> rootList = new ArrayList<>();
        Map<Long, TreeMenu> map = new HashMap<>();
        // 遍历查询结果，组装父子节点关系
        for (TreeMenu menu : menus) {
            map.put(menu.getMenuId(), menu);
            // 第一级节点作为根节点
            if (menu.getMenuLevel() == 1) {
                rootList.add(menu);
                // 非根节点挂在其父节点下
            } else {
                Long parentId = menu.getParentMenuId();
                TreeMenu parentMenu = map.get(parentId);
                if (parentMenu != null) {
                    List<TreeMenu> children = parentMenu.getChildren();
                    if (children == null) {
                        children = new ArrayList<>();
                        parentMenu.setChildren(children);
                    }
                    children.add(menu);
                } else { // 如果找不到父节点，则抛出异常
                    throw new RuntimeException("Parent menu not found: parentId = " + menu.getParentMenuId());
                }
            }
        }
        // 返回结果
        return rootList;
    }

//        // 将数据按照菜单等级排序
//        Collections.sort(list, Comparator.comparingInt(TreeMenu::getMenuLevel));
//        // 将节点按照菜单id放置到map中
//        Map<Long, TreeMenu> map = new HashMap<>();
//        for (TreeMenu menu : list) { // iter
//            map.put(menu.getMenuId(), menu);
//        }
//        // 循环处理每个节点，将每个节点挂在其父节点上
//        List<TreeMenu> rootList = new ArrayList<>();
//        for (TreeMenu menu : list) {
//            // 第一级节点作为根节点
//            if (menu.getMenuLevel() == 1) {
//                rootList.add(menu);
//                // 非根节点挂在其父节点下
//            } else {
//                Long parentId = menu.getParentMenuId();
//                TreeMenu parentMenu = map.get(parentId);
//                if (parentMenu != null) {
//                    List<TreeMenu> children = parentMenu.getChildren();
//                    if (children == null) {
//                        children = new ArrayList<>();
//                        parentMenu.setChildren(children);
//                    }
//                    children.add(menu);
//                } else {
//                    // 兜底处理，抛出异常
//                    throw new RuntimeException("Parent menu not found: parentId = " + parentId);
//                }
//            }
//
//        }
//        return rootList;
//    }
}

