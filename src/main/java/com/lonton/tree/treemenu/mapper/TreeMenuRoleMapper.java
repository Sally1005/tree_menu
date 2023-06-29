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
//    /**
//     * 根据角色菜单
//     *
//     * @param role 角色
//     * @return 菜单数据
//     */
//    List<TreeMenu> getMenuByRole(@PathVariable("role") String role);

    List<TreeMenu> getMenuDataForRoot();

    List<TreeMenu> getMenuDataForMonth();

    List<TreeMenu> getMenuDataForQuarter();

    List<TreeMenu> getMenuDataForYear();

}
