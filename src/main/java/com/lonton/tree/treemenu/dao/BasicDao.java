package com.loo.tree.treemenu.dao;

import java.sql.Connection;
import java.util.List;
import com.loo.tree.treemenu.common.util.ToolDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * 开发BasicDao，作为其他Dao的父类
 *
 * @author
 */
public class BasicDao<T> {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 返回多个对象
     *
     * @param sql SQL语句
     * @param tClass 传入一个类的 Class 对象
     * @param parameters 传入 ? 的具体值，可以有多个
     * @return 根据 Node.class 返回对应的 ArrayList 集合
     */
    public List<T> queryMulti(String sql, Class<T> tClass, Object... parameters) {
        Connection connection = null;
        try {
            connection = ToolDruid.connect();
            List<T> list = queryRunner.query(connection, sql, new BeanListHandler<>(tClass), parameters);
            return list;
        } catch (Exception e) {
             // 将编译异常转为运行异常
            throw new RuntimeException(e);
        } finally {
            ToolDruid.close(null, null, connection);
        }

    }

}
