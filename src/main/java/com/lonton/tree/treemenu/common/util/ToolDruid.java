package com.lonton.tree.treemenu.common.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;

/**
 * 数据库连接池工具类
 * @author 张利红
 */
public class ToolDruid {
    private static DataSource dataSource;
     // 在静态块中完成对 DataSource 的初始化
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src//druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     // 获取 Connection 连接
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try { // 判断是否为空
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
