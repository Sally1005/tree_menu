package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.entity.TreeMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *(TreeMenu)表数据库访问层
 *
 * @author 张利红
 */
@Mapper
@Repository
public interface TreeMenuMapper {
    /**
     * 获取所有数据 <br/>
     * @return 整棵树菜单
     */
    List<TreeMenu> getAllTreeMenu();

    /**
     * 获取根节点菜单 <br/>
     * @return 根节点菜单
     */
    List<TreeMenu> getRootMenus();

    /**
     * 根据父节点查询子节点数据 <br/>
     * @param menuId <br/>
     * @return 子节点
     */
    List<TreeMenu> getChildren(@Param("menuId") Long menuId);

    /**
     * 判断是否是叶子节点 <br/>
     * @param menuId <br/>
     * @return 是否
     */
    Boolean isLeaf(Long menuId);
}

