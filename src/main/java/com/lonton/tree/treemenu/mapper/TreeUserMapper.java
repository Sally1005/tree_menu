package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.TreeUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户管理 Mapper 接口
 * </p>
 *
 * @author 张利红
 */
@Repository
public interface TreeUserMapper{
    /**
     * 查询全部
     * @return
     */
    List<TreeUser> findAll();

}
