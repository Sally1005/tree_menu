package com.lonton.tree.treemenu;

import com.lonton.tree.treemenu.pojo.entity.TreeMenu;
import com.lonton.tree.treemenu.mapper.TreeMenuMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)

class TreeMenuApplicationTests {

    @Autowired
    TreeMenuMapper treeMenuMapper;

    @Test
    void testGetRootMenus() {
        List<TreeMenu> rootTreeMenus = treeMenuMapper.getRootMenus();
        System.out.println(rootTreeMenus);
    }

    @Test
    void testGetChildrenMenus() {
        List<TreeMenu> treeMenus =  treeMenuMapper.getChildren(0L);
        System.out.println(treeMenus);
    }

}
