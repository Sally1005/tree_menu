package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.entity.TreeMenu;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * (TreeMenu)表数据库访问层
 *
 * @author 张利红
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

