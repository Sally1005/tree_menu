package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.entity.User;
import com.lonton.tree.treemenu.pojo.vo.UserLoginInfoVO;
import com.lonton.tree.treemenu.pojo.vo.UserStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
@Slf4j
/**
 * 测试用户部分
 *
 * @author 张利红
 */
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void testInsert(){
        User user = new User();
        user.setId(1L).
                setUserName("root").
                setNickName("超级管理员").
                setPassword("1234");
        log.debug("插入数据之前的参数={}", user);
        int rows =  userMapper.insert( user);
        log.debug("插入数据之后的参数={}", user);
        log.debug("受影响行数={}",rows);
    }

    @Test
    void  testCountByUserName(){
        String username="李浩";
        int count =  userMapper.countByUserName(username);
        log.debug("根据用户名【{}】统计，数量={}", username, count);

    }
    @Test
    void testGetLoginInfoByUsername(){
        String username ="root";
        UserLoginInfoVO loginInfoByUsername =  userMapper.getLoginInfoByUserName(username);
        log.debug("根据用户名【{}】查询用户的登录相关信息：{}", username, loginInfoByUsername);
    }
    @Test
    void testList() {
        List<?> list = userMapper.list();
        log.debug("查询管理员列表，结果集中的数据的数量：{}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }
    @Test
    public  void testGetById(){
        Long id = 5L;
        UserStandardVO result =  userMapper.getStandardById(id);
        log.debug("根据id={}查询完成，查询结果={}", id,result);
    }

    @Test
    public  void  testDeleteById(){
        Long id =5L;
        int rows =  userMapper.deleteById(id);
        log.info("删除完成，受影响的行数=" + rows);
    }

    @Test
    void testUpdate() {
        User  user = new User();
        user.setId(5L);
        user.setEnable(1);

        int rows =  userMapper.update(user);
        log.debug("修改数据完成，受影响的行数={}", rows);
    }

    // 添加断言
}
