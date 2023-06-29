package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.entity.UserRole;
import org.springframework.stereotype.Repository;


/**
 * 处理用户角色的 Mapper接口
 *
 * @author 张利红
 */
@Repository
public interface UserRoleMapper {

    /**
     * 插入用户与角色的关联数据
     *
     * @param userRole 用户与角色的关联数据
     * @return 受影响的行数
     */
    int insert(UserRole userRole);

    /**
     * 批量插入用户与角色的关联数据
     *
     * @param userRoleList 用户与角色的关联数据的列表
     * @return 受影响的行数
     */
    int insertBatch(UserRole[] userRoleList);

    /**
     * 根据用户id删除用户与角色的关联数据
     *
     * @param userId 用户id
     * @return 受影响的行数
     */
    int deleteByUserId(Long userId);

}
