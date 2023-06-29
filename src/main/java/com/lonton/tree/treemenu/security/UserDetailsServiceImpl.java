package com.lonton.tree.treemenu.security;

import com.lonton.tree.treemenu.mapper.UserMapper;
import com.lonton.tree.treemenu.pojo.vo.UserLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户详细信息服务实现类
 *
 * @author 张利红
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        log.debug("根据用户名【{}】从数据库查询用户信息", name);
        // 调用userMapper对象，根据用户名（参数值）查询 用户信息
        UserLoginInfoVO loginInfo = userMapper.getLoginInfoByUserName(name);
        // 判断是否查询到有效结果
        if (loginInfo == null) {
            // 根据用户名没有找到任何 用户信息
            String message = "登录失败，用户名不存在！";
            log.warn(message);
            throw new UsernameNotFoundException("登录失败！");
        }

        log.debug("根据用户名【{}】从数据库查询到有效的用户信息：{}", name, loginInfo);
        // 从查询结果中找出权限信息，转换成Collection<? extends GrantedAuthority>
        List<String> permissions = loginInfo.getPermissions(); // /tree/user/delete
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        // 返回USerDetails类型的对象
        UserDetails userDetails = new UserDetails(
                loginInfo.getUserName(), loginInfo.getPassword(),
                loginInfo.getEnable() == 1, authorities);
        userDetails.setId(loginInfo.getId());

        log.debug("即将向Spring Security返回UserDetails：{}", userDetails);
        return userDetails;
    }
}


