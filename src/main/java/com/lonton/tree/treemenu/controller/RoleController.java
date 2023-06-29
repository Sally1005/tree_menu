package com.lonton.tree.treemenu.controller;

import com.lonton.tree.treemenu.mapper.RoleMapper;
import com.lonton.tree.treemenu.pojo.vo.RoleListItemVO;
import com.lonton.tree.treemenu.security.LoginPrincipal;
import com.lonton.tree.treemenu.web.JsonResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色管理
 *
 * @author 张利红
 */
@Slf4j
@RequestMapping("/roles")
@RestController
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    public RoleController() {
        log.info("创建控制器：RoleController");
    }

    @ApiOperation("查询角色列表")
    @GetMapping("")
    public JsonResult<List<RoleListItemVO>> list(
             @AuthenticationPrincipal LoginPrincipal loginPrincipal
    ) {
        log.debug("准备处理【查询角色列表】的请求");
        log.debug("当前登陆的用户(当事人）的id：{}", loginPrincipal.getId());
        log.debug("当前登陆的用户(当事人）名：{}", loginPrincipal.getUserName());
        List<RoleListItemVO> list = roleMapper.list();
        return JsonResult.ok(list);
    }

}
