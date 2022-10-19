package com.lonton.tree.treemenu.service;

import com.lonton.tree.treemenu.entity.TreeMenu;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class TreeMenuServiceTests {
    @Autowired
    TreeMenuService service;

    @Test
    public void testQueryAll(){
        TreeMenu treeMenu = new TreeMenu();
        List<TreeMenu> treeMenus = service.queryAll();
        log.debug(treeMenus+"");
    }

    @Test
    public void testList() {
        List<TreeMenu> menuList = service.listWithTree();
        for (Object item : menuList) {
            log.debug("{}", item);
        }
    }
}
