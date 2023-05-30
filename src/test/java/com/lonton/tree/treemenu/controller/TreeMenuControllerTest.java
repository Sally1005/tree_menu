package com.lonton.tree.treemenu.controller;


import com.lonton.tree.treemenu.pojo.entity.TreeMenu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 树形菜单控制器层测试
 *
 * @author 张利红
 */
@Slf4j
@SpringBootTest
public class TreeMenuControllerTest {
    @Autowired
    TreeMenuController treeMenuController;

    @Test
    public void testBuildTree() {
        List<TreeMenu> list = new ArrayList<>();
        TreeMenu menu1 = new TreeMenu();
        menu1.setMenuId(1L)
                .setParentMenuId(0L)
                .setMenuName("Root Menu")
                .setMenuLevel(1L);
        TreeMenu menu2 = new TreeMenu();
        menu2.setMenuId(2L)
                .setParentMenuId(1L)
                .setMenuName("Child Menu 1")
                .setMenuLevel(2L);
        TreeMenu menu3 = new TreeMenu();
        menu3.setMenuId(3L)
                .setParentMenuId(1L)
                .setMenuName("Child Menu 2")
                .setMenuLevel(2L);
        list.add(menu2);
        list.add(menu1);
        list.add(menu3);
        List<TreeMenu> result = treeMenuController.buildTree(list);
        assertEquals("Root Menu", result.get(0).getMenuName(), "树形菜单节点属性与期望值不匹配。");

    }

    @Test
    void testSearchItemsEmpty() {
        List<TreeMenu> result1 = treeMenuController.searchItems("不存在的菜单");
        assertEquals(0, result1.size(), "搜索不存在的菜单应该返回空列表。");

    }

    @Test
    void testSearchItemsRoot() {
        List<TreeMenu> result2 = treeMenuController.searchItems("季度");
        assertEquals(1, result2.size(), "搜索根菜单应该返回根菜单本身。");
        assertEquals(0, result2.get(0).getParentMenuId(), "根菜单的父菜单ID应该为0。");

    }

    @Test
    void testSearchItemsChildren() {
        List<TreeMenu> result3 = treeMenuController.searchItems("工业");
        assertEquals(3, result3.size(), "搜索子菜单应该返回子菜单和其父菜单。");
        // 这里应该是没有指定包括 "工业" 关键词到底是属于哪一级别的菜单
        assertEquals(0, result3.get(0).getParentMenuId(), "子菜单的父菜单ID应该为0。");
        assertEquals(0, result3.get(1).getParentMenuId(), "子菜单的父菜单的父菜单ID应该为0。");

    }

}
