package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.vo.RoleListItemVO;
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

}
