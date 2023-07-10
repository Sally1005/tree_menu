package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.entity.TreeMenu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 树形菜单Mapper层测试
 *
 * @author 张利红
 */
@Slf4j
@SpringBootTest
public class TreeMenuMapperTest {
    @Autowired
    TreeMenuMapper treeMenuMapper;

    @Test
    public void testGetAllTreeMenu() {
        List<TreeMenu> allTreeMenus =  treeMenuMapper.getAllTreeMenu();
        assertEquals(133,allTreeMenus.size(),"树形菜单节点的个数与实际个数不符。");
    }

    @Test
    public void testGetRootMenus() {
        List<TreeMenu> rootMenus = treeMenuMapper.getRootMenus();
        assertEquals(3,rootMenus.size(),"根节点的个数与实际个数不符。");
    }

    @Test
    public void testGetChildrenMenus() {
        // parent_menu_id = 1L
        List<TreeMenu> menus =  treeMenuMapper.getChildren(1L);
        assertEquals(7,menus.size(),"子节点的个数与实际个数不符。");
    }

    @Test
    public void testGetMenuById() {
        TreeMenu menu = treeMenuMapper.getMenuById(1L);
        TreeMenu expected = new TreeMenu();
        expected.setMenuId(1L)
                .setParentMenuId(0L)
                .setMenuName("月度")
                .setMenuLevel(1L)
                .setIsLeaf(false);
        assertEquals(expected.getMenuId(),menu.getMenuId(),"树形菜单节点属性与期望值不匹配。");

    }

    @Test
    public void testSearchItems() {
        List<TreeMenu> items =  treeMenuMapper.searchItems("年度");
        // 检查列表中是否只有一个元素
        assertEquals(1, items.size(),
                "期望只有一个树形菜单节点，但找到了 " + items.size() + " 个");
        TreeMenu expected = new TreeMenu();
        expected.setMenuId(100L)
                .setParentMenuId(0L)
                .setMenuName("年度")
                .setMenuLevel(1L)
                .setIsLeaf(false);
        // 检查列表中的单个元素是否具有期望的属性
        assertEquals(expected.toString(),items.get(0).toString(),"树形菜单节点属性与期望值不匹配。");
    }
}
