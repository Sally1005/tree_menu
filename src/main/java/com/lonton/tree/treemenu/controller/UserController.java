package com.lonton.tree.treemenu.controller;


import com.alibaba.fastjson.JSON;
import com.lonton.tree.treemenu.common.exception.GlobalException;
import com.lonton.tree.treemenu.mapper.UserMapper;
import com.lonton.tree.treemenu.mapper.UserRoleMapper;
import com.lonton.tree.treemenu.pojo.dto.UserAddNewDTO;
import com.lonton.tree.treemenu.pojo.dto.UserLoginDTO;
import com.lonton.tree.treemenu.pojo.entity.*;
import com.lonton.tree.treemenu.pojo.vo.UserListItemVO;
import com.lonton.tree.treemenu.pojo.vo.UserLoginVO;
import com.lonton.tree.treemenu.pojo.vo.UserStandardVO;
import com.lonton.tree.treemenu.security.LoginPrincipal;
import com.lonton.tree.treemenu.security.UserDetails;
import com.lonton.tree.treemenu.service.DynamicMenuService;
import com.lonton.tree.treemenu.service.MenuService;
import com.lonton.tree.treemenu.service.RoleService;
import com.lonton.tree.treemenu.web.JsonResult;
import com.lonton.tree.treemenu.web.ServiceCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.*;
/**
 * 用户管理
 *
 * @author 张利红
 */

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    AuthenticationManager authenticationManager;
    @Value("${tree-menu.jwt.secret-key}")
    private String secretKey;
    @Resource
    private MenuService menuService;
    @Resource
    private DynamicMenuService dynamicMenuService;
    @Resource
    private RoleService roleService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public JsonResult<UserLoginVO> login(@Validated UserLoginDTO userLoginDTO) {
        // 检查用户名是否为空
        if (userLoginDTO.getUserName() == null || userLoginDTO.getUserName().trim().isEmpty()) {
            return JsonResult.error(ServiceCode.ERR_NOT_FOUND,"用户名不能为空。");
        }
        // 日志
        log.debug("开始处理【用户登录】的请求：{}", userLoginDTO);

        // 处理用户登录
        UserDetails loginUser = this.doLogin(userLoginDTO);
        // 生成jwt
        String userCertificate = this.generateUserCertificate(loginUser);
        // 查询用户树形菜单
        List<TreeDynamicMenu> treeMenuList = dynamicMenuService.findTreeMenuListByUserId(loginUser.getId());
        // 查询用户角色列表
        List<Role> roleList = roleService.findListByUserId(loginUser.getId());
        UserLoginVO userLoginVo = UserLoginVO.builder()
                .jwt(userCertificate)
                .menuList(treeMenuList)
                .roleList(roleList)
                .build();
        return JsonResult.ok(userLoginVo);
    }

    /**
     * 生成用户凭证
     *
     * @param loginUser 登录用户
     * @return 凭证
     */
    private String generateUserCertificate(UserDetails loginUser) {
        Collection<GrantedAuthority> authorities = loginUser.getAuthorities();
        // 【重要】将权限列表转换成JSON格式，用于存储到JWT中
        String authorityListString = JSON.toJSONString(authorities);
        // 生成JWT
        log.debug("开始生成secretKey:{}", secretKey);
        // 准备Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginUser.getId());
        claims.put("authorities", authorityListString);
        claims.put("username", loginUser.getUsername());
        log.debug("生成JWT，向JWT中存入id：{}", loginUser.getId());
        log.debug("生成JWT，向JWT中存入authorities：{}", authorityListString);
        // JWT的组成部分：Header（头），Payload（载荷），Signature（签名）
        String jwt = Jwts.builder()
                // Header：用于声明算法与此数据的类型，以下配置的属性名是固定的
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "jwt")
                // Payload：用于添加自定义数据，并声明有效期
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000))
                // Signature：用于指定算法与密钥（盐）
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        log.debug("生成的JWT：{}", jwt);
        return jwt;
    }

    private UserDetails doLogin(UserLoginDTO userLoginDTO) {
        // 日志
        log.debug("开始处理【用户登录】的业务，参数：{}", userLoginDTO);
        //调用authenticationManager执行认证
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUserName(), userLoginDTO.getPassword());
        authenticationManager.authenticate(authentication);
        log.debug("认证通过");
        Authentication authenticateResult = authenticationManager.authenticate(authentication);
        log.debug("认证通过，返回的结果：{}", authenticateResult);
        log.debug("认证通过返回的结果Principal的类型：{}",
                authenticateResult.getPrincipal().getClass().getName());
        UserDetails loginUser = (UserDetails) authenticateResult.getPrincipal();
        log.debug("认证结果中的用户：{}", loginUser.getUsername());
        Collection<GrantedAuthority> authorities = loginUser.getAuthorities();
        log.debug("认证结果中的权限列表：{}", authorities);

        return loginUser;
    }

    @ApiOperation("添加用户")
    @PostMapping("/add-new")//检查 数据多 涉及密码
    @PreAuthorize("hasAuthority('/tree/user/update')")
    public JsonResult<Void> addNew(@Validated UserAddNewDTO userAddNewDTO,
                                   @ApiIgnore @AuthenticationPrincipal LoginPrincipal loginPrincipal) {
        // 日志
        log.debug("开始处理【添加用户】的请求：{}", userAddNewDTO);
        log.debug("当前登陆的用户(当事人）的id：{}", loginPrincipal.getId());
        log.debug("当前登陆的用户(当事人）名：{}", loginPrincipal.getUserName());
        // 调用Service对象实现添加
        addNew(userAddNewDTO);
        // 返回
        return JsonResult.ok();
    }

    private void addNew(UserAddNewDTO userAddNewDTO) {
        // 日志
        log.debug("开始处理【添加用户】的业务，参数：{}", userAddNewDTO);
        // 从参数中获取尝试添加的用户的用户名
        String username = userAddNewDTO.getUserName();
        // 调用userMapper对象的countByUsername()方法进行统计
        int countByUsername = userMapper.countByUserName(username);
        // 判断统计结果是否大于0
        if (countByUsername > 0) {
            // 是：日志，抛出ServiceException
            String message = "添加用户失败，用户名【" + username + "】已经被占用！";
            log.warn(message);
            throw new GlobalException(message, ServiceCode.ERR_CONFLICT);
        }

        // 创建新的user对象
        User user = new User();

        // 调用BeanUtils.copyProperties()方法将参数的属性值复制到以上user对象中
        BeanUtils.copyProperties(userAddNewDTO,user);

        //对原密码进行加密
        String rawPassword = user.getPassword();
        String encodePassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodePassword);

        // 日志
        log.debug("即将插入用户数据：{}", user);
        // 调用userMapper对象的insert()方法插入数据，并获取返回的受影响的行数
        int rows = userMapper.insert(user);
        // 判断受影响的行数是否不等于1
        if (rows != 1) {
            // 是：日志，抛出ServiceException
            String message = "添加用户失败，服务器忙，请稍后再次尝试！[错误代码：1]";
            log.warn(message);
            throw new GlobalException(message, ServiceCode.ERR_INSERT);
        }

        // 向用户与角色关联的表中插入数据
        log.debug("准备向用户与角色关联的表中插入数据");
        Long userId = user.getId();
        Long[] roleIds = userAddNewDTO.getRoleIds();

        UserRole[] userRoleList = new UserRole[roleIds.length];
        for (int i = 0; i < roleIds.length; i++) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleIds[i]);
            userRoleList[i] = userRole;
        }
        rows = userRoleMapper.insertBatch(userRoleList);
        // 判断受影响的行数是否小于1（可能插入多条数据，所以，大于或等于1的值均视为正确）
        if (rows < 1) {
            // 是：日志，抛出ServiceException
            String message = "添加用户失败，服务器忙，请稍后再次尝试！[错误代码：2]";
            log.warn(message);
            throw new GlobalException(message, ServiceCode.ERR_INSERT);
        }
    }

    @ApiOperation("根据id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "long")
    @PreAuthorize("hasAuthority('/tree/user/delete')")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult deleteById(@PathVariable Long id) {
        log.debug("准备处理【根据id删除用户】的请求：id={}", id);
        deleteByIdd(id);
        return JsonResult.ok();
    }

    private void deleteByIdd(Long id) {
        log.debug("开始处理【根据id删除用户】的业务：id={}", id);
        // 调用userMapper根据参数id执行查询
        UserStandardVO queryResult = userMapper.getStandardById(id);
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 抛出ServiceException，业务状态码：40400
            String message = "删除用户失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new GlobalException(message, ServiceCode.ERR_NOT_FOUND);
        }
        // 调用userMapper根据参数id删除用户的数据，并获取返回值
        int rows = userMapper.deleteById(id);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 抛出ServiceException，业务状态码：DELETE对应的常量
            String message = "删除用户失败！服务器忙，请稍后再次尝试！[错误代码：1]";
            log.warn(message);
            throw new GlobalException(message, ServiceCode.ERR_DELETE);
        }
    }

    @ApiOperation("启用用户账号")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "long")
    @PreAuthorize("hasAuthority('/tree/user/update')")
    @PostMapping("/{id:[0-9]+}/enable")
    public JsonResult<Void> setEnable(@PathVariable Long id) {
        log.debug("准备处理【启用用户账号】的请求：id={}", id);
        userMapper.setEnable(id);
        return JsonResult.ok();
    }

    @ApiOperation("禁用用户账号")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "long")
    @PreAuthorize("hasAuthority('/tree/user/update')")
    @PostMapping("/{id:[0-9]+}/disable")
    public JsonResult<Void> setDisable(@PathVariable Long id) {
        log.debug("准备处理【禁用用户账号】的请求：id={}", id);
        userMapper.setDisable(id);
        return JsonResult.ok();
    }

    @ApiOperation("查询用户列表")
    @PreAuthorize("hasAuthority('/tree/user/read')")
    @GetMapping("")
    public JsonResult<List<UserListItemVO>> list() {
        log.debug("准备处理【查询用户列表】的请求");
        List<UserListItemVO> list = userMapper.list();
        return JsonResult.ok(list);
    }
}
