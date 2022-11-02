package com.lonton.tree.treemenu.controller;

import com.alibaba.fastjson.JSONObject;
import com.lonton.tree.treemenu.common.util.Result;
import com.lonton.tree.treemenu.entity.TreeMenu;
import com.lonton.tree.treemenu.mapper.TreeMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
     * 获取所有数据
     * @return
     */
    private  List<TreeMenu> getAllTreeMenu(){
        log.debug("开始处理获取所有数据请求");
        return treeMenuMapper.getAllTreeMenu();
    }

    /**
     * 获取数据库数据，并处理成树形结构
     * @return 树形结构数据
     */
    @GetMapping("getMenuTree")
    private Result queryAllWithTree() {
        log.debug("开始处理将获取数据处理成树形结构请求");
        return Result.ok().data("items",getMenuTree());
    }
    /**
     * 按层排序取数:返回树形数据
     * @return
     */
    public List<TreeMenu> getMenuTree() {
        List<TreeMenu> allTreeMenu = getAllTreeMenu();
         // 最高级别集合
        List<TreeMenu> roots = new ArrayList<>();
        List<TreeMenu> res = new ArrayList<>();
        for (TreeMenu treeMenu : allTreeMenu) {
             // 1 表示最高级别
            if(treeMenu.getMenuLevel() == 1){
                roots.add(treeMenu);
            }
        }
         // 从最高级别用户开始遍历，递归找到该用户的下级用户，将带有下级的最高级用户放入返回结果中
        for (TreeMenu treeMenu : roots){
             treeMenu= buildMenuTree(allTreeMenu, treeMenu);
             res.add(treeMenu);
        }
        return res;
    }
    /**
     * 根据传入的用户，获取该用户的下属集合，将集合赋值给该用户的children属性，返回该用户
     * @param allTreeMenus
     * @param treeMenu
     * @return
     */
    public  TreeMenu buildMenuTree(List<TreeMenu> allTreeMenus,TreeMenu treeMenu){
        List<TreeMenu> children = new ArrayList<>();
        for (TreeMenu treeMenu1 : allTreeMenus){
            if (treeMenu1.getMenuLevel() == 1)
                continue;
             // 当前用户的上级编号和传入的用户编号相等，表示该用户是传入用户的下级用户
            if(treeMenu1.getParentMenuId() == treeMenu.getMenuId()){
                 // 递归调用，再去寻找该用户的下级用户
                treeMenu1 = buildMenuTree(allTreeMenus, treeMenu1);
                // 当前用户是该用户的一个下级用户，放入children集合内
                children.add(treeMenu1);
            }
        }
        // 给该用户的children属性赋值，并返回该用户
        treeMenu.setChildren(children);
        return treeMenu;
    }

    /**
     * 根据名称查询数
     * @return
     */
    @RequestMapping("selectByMenuName")
    public Result selectByMenuName( String menuName){
        log.debug("开始处理【根据名称查询树形菜单】的请求");
        return Result.ok().data("items",treeMenuMapper.selectByMenuName(menuName));
    }

/*
 public Result<List<JSONObject>> tree() {
        List<JSONObject> list=treeMenuMapper.tree();
        System.out.println(list);
        return Result.ok(list);
    }

    public Result<List<JSONObject>> tree2(Map<String, Object> user) {
        int menuLevel=Integer.parseInt(user.get("id").toString()) ;
        List<JSONObject> list=treeMenuMapper.tree2(menuLevel);
        return Result.ok(list);
    }
 */


}

