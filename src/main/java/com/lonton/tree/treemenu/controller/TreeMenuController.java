package com.lonton.tree.treemenu.controller;

import com.lonton.tree.treemenu.common.util.Result;
import com.lonton.tree.treemenu.entity.TreeMenu;
import com.lonton.tree.treemenu.service.TreeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (TreeMenu)表控制层
 *
 * @author 张利红
 * date:2022-11-09
 */
@RestController
@RequestMapping("treeMenu")
public class TreeMenuController {

    @Autowired
    private TreeMenuService treeMenuService;

    /**
     * 获取根节点菜单
     * @return
     */
    @GetMapping("")
    public Result getRootMenus() {
        List<TreeMenu> menus = treeMenuService.getRootMenus();
        for (TreeMenu menu : menus) {
            Boolean leaf = treeMenuService.isLeaf(menu.getMenuId());
            menu.setIsLeaf(leaf);
        }
        return Result.ok().data("menus", menus);
    }

    /**
     * 根据父节点查询子节点数据
     * @param menuId
     * @return
     */
    @GetMapping("/{menuId}")
    public Result getChildren(@PathVariable Long menuId){
        List<TreeMenu> menus = treeMenuService.getChildren(menuId);
        for (TreeMenu menu : menus) {
            Boolean leaf = treeMenuService.isLeaf(menu.getMenuId());
            menu.setIsLeaf(leaf);
        }
        return Result.ok().data("menus", menus);
    }

    /**
     * 根据名称查询
     * @param menuName
     * @return
     */
    @GetMapping("/search/{menuName}")
    public Result searchItems(@PathVariable String menuName){
        List<TreeMenu> menus = treeMenuService.searchItems(menuName);
        return Result.ok().data("menus", menus);
    }

}

