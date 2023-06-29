package com.lonton.tree.treemenu.mapper;

import com.lonton.tree.treemenu.pojo.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
/**
 * 测试用户角色部分
 *
 * @author 张利红
 */
public class UserRoleMapperTest {
    @Autowired
    UserRoleMapper userRoleMapper;

    public void testInsert() {
        UserRole userRole = new UserRole();
        userRole.setUserId(10L);
        userRole.setRoleId(20L);

        log.debug("插入数据之前，参数：{}", userRole);
        int rows = userRoleMapper.insert(userRole);
        log.debug("rows = {}", rows);
        log.debug("插入数据之后，参数：{}",userRole);
    }

    @Test
    public void testInsertBatch() {
        Long userId = 5L;
        Long[] roleIds = {2L, 3L, 4L};

        UserRole[] userRoleList = new UserRole[roleIds.length];
        for (int i = 0; i < roleIds.length; i++) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleIds[i]);
            userRoleList[i] = userRole;
        }
        int rows = userRoleMapper.insertBatch(userRoleList);
        log.debug("批量插入管理员与角色的关联数据成功，受影响的行数={}", rows);
    }

    @Test
    public  void  testDeleteByUserId(){
        Long UserId =5L;
        int rows = userRoleMapper.deleteByUserId(UserId);
        log.info("删除完成，受影响的行数=" + rows);
    }

    // 添加断言比对

}
