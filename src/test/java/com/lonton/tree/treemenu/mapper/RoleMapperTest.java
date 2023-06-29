package com.lonton.tree.treemenu.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 测试角色部分
 *
 * @author 张利红
 */
@Slf4j
@SpringBootTest
public class RoleMapperTest {
    @Autowired
    RoleMapper roleMapper;

    @Test
    public void testList(){
        List<?> list = roleMapper.list();
        log.debug("查询列表完成，结果集中的数据的数量="+list.size()); // 集合名+.for(快捷键）
        for (Object item : list) {
            log.debug("{}",item);
        }
        // 断言

    }
}
