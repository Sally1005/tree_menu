package com.lonton.tree.treemenu.mapper;

import com.alibaba.fastjson.JSONObject;
import com.lonton.tree.treemenu.entity.TreeMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (TreeMenu)表数据库访问层
 *
 * @author 张利红
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
     * 树形数据返回数据
     * @return
     */
    List<TreeMenu> getMenuTree();

    void  buildMenuTree();

    /**
     * 根据名称查询数据
     * @param
     * @return
     */
    List<TreeMenu> selectByMenuName(String menuName);
    /**
     * 树
     */
    List<JSONObject> tree();
    List<JSONObject> tree2(@Param("menuLevel")Integer menuLevel);

}

