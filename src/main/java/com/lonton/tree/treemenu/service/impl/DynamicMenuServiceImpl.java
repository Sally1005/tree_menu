package com.lonton.tree.treemenu.service.impl;

import com.lonton.tree.treemenu.mapper.TreeDynamicMenuMapper;
import com.lonton.tree.treemenu.pojo.entity.TreeDynamicMenu;
import com.lonton.tree.treemenu.service.DynamicMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态菜单实现类
 *
 * @author 张利红
 */
@Service
public class DynamicMenuServiceImpl implements DynamicMenuService {

    @Resource
    private TreeDynamicMenuMapper dynamicMenuMapper;

    @Override
    public List<TreeDynamicMenu> findTreeMenuListByUserId(Long userId) {
        // 1. 查询当前用户角色关联的菜单
        List<TreeDynamicMenu> menuList = dynamicMenuMapper.findListByUserId(userId);
        // 2. 封装成树形结构
        return this.buildTree(menuList);
    }


    /**
     * 构建菜单树
     *
     * @param list 菜单列表
     * @return 树结构菜单
     */
    private List<TreeDynamicMenu> buildTree(List<TreeDynamicMenu> list) {
        List<TreeDynamicMenu> root = new ArrayList<>();
        // 有序排列
        Map<Long, TreeDynamicMenu> menuMap = new LinkedHashMap<>();
        // 遍历列表
        for (TreeDynamicMenu menu : list) {
            // 将菜单添加到menuMap中
            menuMap.put(menu.getMenuId(), menu);
            // 检查是否为根节点
            if (menu.getParentMenuId() == 0L) {
                root.add(menu);
            } else {
                // 获取父菜单并将当前菜单添加到其子菜单中
                TreeDynamicMenu parentMenu = menuMap.get(menu.getParentMenuId());
                if (parentMenu != null) {
                    parentMenu.getChildren().add(menu);
                }
            }
        }
        // 返回根节点
        return root;
    }

}
