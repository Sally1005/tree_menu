package com.loo.tree.treemenu.service;

import com.loo.tree.treemenu.entity.TreeMenu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (TreeMenu)表服务接口
 *
 * @author
 */
@Transactional
public interface ITreeMenuService {

    /**
     * 查询数据库所有数据
     * @return 所有数据
     */
    List<TreeMenu> queryAll();

    /**
     * 查询数据库数据，并处理后返回 树形数据
     *
     * @return 树形数据
     */
    List<TreeMenu> listWithTree();

    /**
     * 获取子菜单
     *
     * @param root 父菜单
     * @param all 总的数据
     * @return 子菜单
     */
    List<TreeMenu> getChildren(TreeMenu root ,List<TreeMenu> all);

}
