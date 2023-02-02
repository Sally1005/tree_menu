package com.lonton.tree.treemenu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.lonton.tree.treemenu.mapper"})
public class TreeMenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(TreeMenuApplication.class, args);
    }

}
