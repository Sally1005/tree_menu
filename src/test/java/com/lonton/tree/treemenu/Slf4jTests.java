package com.lonton.tree.treemenu;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Slf4j
@SpringBootTest
public class Slf4jTests {
    @Test
    void testSlf4j(){
        log.trace("这是一条【debug】级别的日志"); // 默认级别已改为debug，debug级别以下将不会在控制台输出
        log.debug("这是一条【debug】级别的日志");
        log.info("这是一条【info】级别的日志");
        log.warn("这是一条【warn】级别的日志");
        log.error("这是一条【error】级别的日志");

        int a = 3;
        int b = 5;
        log.info("a+b={}",a+b);
    }
}
