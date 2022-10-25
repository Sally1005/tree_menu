package com.loo.tree.treemenu.controller;

import com.loo.tree.treemenu.common.util.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * (TreeMenu)表控制层
 *
 * @author
 */
@RestController
@RequestMapping("treeMenu")
public class TreeMenuController {
    /**
     * 服务对象
     */
    @Resource
    private com.loo.tree.treemenu.service.ITreeMenuService ITreeMenuService;

    /**
     * 获取数据库数据，并处理成树形结构
     * @return 树形结构数据
     */
     // 解决跨域问题
    @CrossOrigin
    @GetMapping("selectAllWithTree")
    public Result selectAllWithTree() {
        return Result.ok().data("items", ITreeMenuService.listWithTree());
    }

    /**
     * 获取数据库所有数据
     * @return 所有数据
     */
    @GetMapping("selectAll")
    public Result selectAll() {
        return Result.ok().data("items", ITreeMenuService.queryAll());
    }


}

