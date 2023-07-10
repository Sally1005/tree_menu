package com.lonton.tree.treemenu.service.impl;

import com.lonton.tree.treemenu.mapper.RoleMapper;
import com.lonton.tree.treemenu.pojo.entity.Role;
import com.lonton.tree.treemenu.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色实现类
 *
 * @author 张利红
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 根据用户Id查询角色列表
     *
     * @param userId 用户Id
     * @return 角色列表
     */
    @Override
    public List<Role> findListByUserId(Long userId) {
        return roleMapper.findListByUserId(userId);
    }
}
