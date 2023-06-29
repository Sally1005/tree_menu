package com.lonton.tree.treemenu;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 测试 BCrypt
 *
 * @author 张利红
 */
@Slf4j
public class BCryptTest {

    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Test
    void testBCrypt(){
        for (int i = 0; i < 5; i++) {
            String rawPassword = "1234";
            String encodePassword = passwordEncoder.encode(rawPassword);
            log.debug("原文={},密文={}",rawPassword,encodePassword);
        }
    }
    @Test
    void testMatches(){
        String rawPassword = "123456";
        String encodePassword="$2a$10$vmVHBvm.5fsGqM7njBVlBuhz1y/hVF/bEr.tfBoZTC08v8.TouGJq";
        boolean result = passwordEncoder.matches(rawPassword, encodePassword);
        log.debug("原文={},密文={},验证结果={}",rawPassword,encodePassword,result);
    }

}
