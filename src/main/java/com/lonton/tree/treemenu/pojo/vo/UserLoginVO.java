package com.lonton.tree.treemenu.pojo.vo;

import com.lonton.tree.treemenu.pojo.entity.Role;
import com.lonton.tree.treemenu.pojo.entity.TreeDynamicMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVO {
    /**
     * 用户凭证
     */
    private String jwt;
    /**
     * 菜单列表
     */
    private List<TreeDynamicMenu> menuList;
    /**
     * 角色列表
     */
    private List<Role> roleList;
}
