package com.loo.tree.treemenu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;


@SpringBootTest
class TreeMenuApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    DataSource dataSource;

    @Test
    void testConnection() throws Throwable {
        dataSource.getConnection();
    }

}
