package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.entity.TreeMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *(TreeMenu)表数据库访问层
 *
 * @author 张利红
 * date:2022-11-09
 */
@Mapper
@Repository
public interface TreeMenuMapper {
    /**
     * 获取所有数据
     * @return
     */
    List<TreeMenu> getAllTreeMenu();

    /**
     * 获取根节点菜单
     * @return
     */
    List<TreeMenu> getRootMenus();

    /**
     * 根据父节点查询子节点数据
     * @param menuId
     * @return
     */
    List<TreeMenu> getChildren(@Param("menuId") Long menuId);
}

