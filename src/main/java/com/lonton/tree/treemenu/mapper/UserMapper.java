package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.entity.User;
import com.lonton.tree.treemenu.pojo.vo.UserListItemVO;
import com.lonton.tree.treemenu.pojo.vo.UserLoginInfoVO;
import com.lonton.tree.treemenu.pojo.vo.UserStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 处理用户数据的Mapper接口
 *
 * @author 张利红
 */
@Repository
public interface UserMapper {
    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @return 受影响的行数
     */
    int insert(User user);

    /**
     * 更新用户数据
     *
     * @param user 用户数据
     * @return 受影响的行数
     */
    int update(User user);

    /**
     * 根据id删除用户数据
     *
     * @param id 用户id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据用户名统计管理员的数量
     *
     * @param userName 用户名
     * @return 匹配用户名的数据
     */
    int countByUserName(String userName);

    /**
     * 根据id查询用户详情
     *
     * @param id 用户id
     * @return 匹配的用户详情，如果没有匹配的数据，则返回null
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 根据用户名查询用户的登录信息
     *
     * @param userName 用户名
     * @return 匹配的用户详情，如果没有匹配的数据，则返回null
     */
    UserLoginInfoVO getLoginInfoByUserName(String userName);

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    List<UserListItemVO> list();


    /**
     * 启用用户账号
     *
     * @param id 用户id
     */
    void setEnable(Long id);

    /**
     * 禁用用户账号
     *
     * @param id 用户id
     */
    void setDisable(Long id);
}
