package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.entity.TreeMenu;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 树形菜单 Mapper类
 *
 * @author 张利红
 */
@Repository
public interface TreeMenuMapper {
    /**
     * 获取所有数据
     *
     * @return 树形菜单
     */
    List<TreeMenu> getAllTreeMenu();

    /**
     * 获取根节点菜单
     *
     * @return 根节点菜单
     */
    List<TreeMenu> getRootMenus();

    /**
     * 根据父节点查询子节点数据
     *
     * @param menuId 节点id
     * @return 子节点
     */
    List<TreeMenu> getChildren(@Param("menuId") Long menuId);

    /**
     * 根据菜单Id查询
     *
     * @param menuId 菜单id
     * @return 菜单树
     */
    TreeMenu getMenuById(@Param("menuId")Long menuId);

    /**
     * 根据菜单名称查询
     *
     * @param menuName 菜单名
     * @return 菜单树
     */
    List<TreeMenu> searchItems(@Param("menuName") String menuName);

    /**
     * 根据用户Id查询菜单
     *
     * @param userId 用户Id
     * @return 菜单树
     */
    List<TreeMenu> findListByUserId(@Param("userId") Long userId);
}

