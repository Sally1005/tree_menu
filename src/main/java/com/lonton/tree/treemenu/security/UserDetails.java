package com.lonton.tree.treemenu.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 用户详情
 *
 * @author 张利红
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString(callSuper = true)
public class UserDetails extends User {

    /**
     * 新增属性，用户id，唯一标识
     */
    private Long id;

    /**
     * 调用父类构造方法，初始化用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @param enabled 账户是否启用
     * @param authorities 权限
     */
    public UserDetails(String username, String password, boolean enabled,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled,
                true, true, true,
                authorities);
    }
}
