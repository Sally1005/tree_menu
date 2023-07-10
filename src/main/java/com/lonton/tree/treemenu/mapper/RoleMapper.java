package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.entity.Role;
import com.lonton.tree.treemenu.pojo.vo.RoleListItemVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理角色数据的Mapper接口
 *
 * @author 张利红
 */
@Repository
public interface RoleMapper {
    /**
     * 查询角色列表
     *
     * @return 角色列表
     */
    List<RoleListItemVO> list();

    /**
     * 查询当前用户下的角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> findListByUserId(@Param("userId") Long userId);
}
