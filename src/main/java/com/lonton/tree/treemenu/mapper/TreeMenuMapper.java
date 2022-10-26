package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.entity.TreeMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TreeMenu)表数据库访问层
 *
 * @author 张利红
 */
@Mapper
@Repository
public interface TreeMenuMapper {
    /**
     * 查询所有数据
     *
     * @param treeMenu 实例对象
     * @return 对象列表
     */
    List<TreeMenu> queryAll(TreeMenu treeMenu);
    /**
     * 查询数据库数据，并处理后返回 树形数据
     *
     * @return 树形数据
     */
    List<TreeMenu> listWithTree(TreeMenu treeMenu);

    /**
     * 根据名称查询数据
     * @param wd 输入名称
     * @return
     */
    List<TreeMenu> selectByWd(String wd);

}

