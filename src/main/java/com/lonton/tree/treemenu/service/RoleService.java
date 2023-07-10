package com.lonton.tree.treemenu.service;

import com.lonton.tree.treemenu.pojo.entity.Role;

import java.util.List;


/**
 * 角色接口
 *
 * @author 张利红
 */
public interface RoleService {
    /**
     * 查询当前用户下的角色列表
     *
     * @return 角色列表
     */
    List<Role> findListByUserId(Long userId);
}
