package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.entity.TreeMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理菜单角色的Mapper接口
 *
 * @author 张利红
 */
@Repository
public interface TreeMenuRoleMapper {

    /**
     * 获取超级管理员菜单数据
     *
     * @return 超级管理员权限下可展示的菜单树
     */
    List<TreeMenu> getMenuDataForRoot();

    /**
     * 获取月度管理员菜单数据
     *
     * @return 月度管理员权限下可展示的菜单树
     */
    List<TreeMenu> getMenuDataForMonth();

    /**
     * 获取季度管理员菜单数据
     *
     * @return 季度管理员权限下可展示的菜单树
     */
    List<TreeMenu> getMenuDataForQuarter();

    /**
     * 获取年度管理员菜单数据
     *
     * @return 年度管理员权限下可展示的菜单树
     */
    List<TreeMenu> getMenuDataForYear();

}
