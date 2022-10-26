package com.lonton.tree.treemenu.controller;

import com.lonton.tree.treemenu.common.util.Result;
import com.lonton.tree.treemenu.entity.TreeMenu;
import com.lonton.tree.treemenu.mapper.TreeMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * (TreeMenu)表控制层
 *
 * @author 张利红
 */
@RestController
@RequestMapping("treeMenu")
@Slf4j
@CrossOrigin
public class TreeMenuController {
    /**
     * 服务对象
     */
    @Autowired
    TreeMenuMapper treeMenuMapper;

    /**
     * 获取数据库数据，并处理成树形结构
     * @return 树形结构数据
     */


    @GetMapping("selectAllWithTree")
    public Result selectAllWithTree() {
        return Result.ok().data("items", listWithTree());
    }
    /**
     * 获取数据库所有数据
     * @return 所有数据
     */
    @GetMapping("selectAll")
    public Result selectAll() {
        return Result.ok().data("items",queryAll());
    }

    /**
     * 根据名称查询数据
     * @param wd 用户输入名称
     * @return
     */
    @RequestMapping("selectByWd")
    public Result selectByWd( String wd){
        return Result.ok().data("items",treeMenuMapper.selectByWd(wd));
    }

    /**
     * 查询数据库所有数据
     *
     * @return 数据库所有数据
     */

    public List queryAll() {
        return treeMenuMapper.queryAll(null);
    }

    /**
     * 查询数据库数据，并处理后返回树形数据
     *
     * @return 树形数据
     */

    public List<TreeMenu> listWithTree() {
        // 查找所有菜单数据
        List<TreeMenu> lists = treeMenuMapper.queryAll(null);
        // 把数据组合成树形结构
        List result = lists.stream().filter(treeMenu -> treeMenu.getMenuLevel() == 1)
                // 查找子菜单并放到第一级菜单中
                .map(menu -> {
                    menu.setTreeMenu(getChildren(menu, lists));
                    return menu;
                })
                // 根据排序字段排序
                .sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                // 把处理结果收集成一个 List 集合
                .collect(Collectors.toList());
        return result;
    }
    /**
     * 递归获取子菜单
     *
     * @param root 父菜单
     * @param all 总的数据
     * @return 子菜单
     */
    public List<TreeMenu> getChildren(TreeMenu root ,List<TreeMenu> all){
        List<TreeMenu> children = all.stream()
                // 根据父菜单 ID 查找当前菜单 ID，以便于找到当前菜单的子菜单
                .filter(menu ->menu.getParentMenuId() == root.getMenuId())
                // 递归查找子菜单的子菜单
                .map((menu) -> {
                    menu.setTreeMenu(getChildren(menu,all));
                    return menu;
                })
                // 根据排序字段排序
                .sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                // 把处理结果收集成一个 List 集合
                .collect(Collectors.toList());
        return children;
    }


}

