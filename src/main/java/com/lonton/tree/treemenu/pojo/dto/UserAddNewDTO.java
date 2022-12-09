package com.lonton.tree.treemenu.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加用户的DTO类
 * @author 张利红
 */
@Data
public class UserAddNewDTO implements Serializable {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "必须提交用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "必须提交密码")
    private String password;


    /**
     * 用户关联到的若干个角色的id
     */
    @ApiModelProperty("用户关联到的若干个角色的id")
    private Long[] roleIds;

}
