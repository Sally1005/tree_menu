package com.loo.tree.treemenu.mapper;

import com.loo.tree.treemenu.entity.TreeMenu;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * (TreeMenu)表数据库访问层
 *
 * @author
 */
@Mapper
public interface TreeMenuMapper {
    /**
     * 查询所有数据
     *
     * @param treeMenu 实例对象
     * @return 对象列表
     */
    List<TreeMenu> queryAll(TreeMenu treeMenu);

}

