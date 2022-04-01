package com.axx.myssm.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {
    //本地线程
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /*public static String DRIVER;
    public static String URL;
    public static String USER;
    public static String PWD;*/
    static Properties properties = new Properties();

    static {
        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(is);

            /*DRIVER = properties.getProperty("DRIVER");
            URL = properties.getProperty("URL");
            USER = properties.getProperty("USER");
            PWD = properties.getProperty("PWD");*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //创建连接
    private static Connection createConn() {
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

            /*DruidDataSource druidDataSource=new DruidDataSource();
            druidDataSource.setDriverClassName(DRIVER);
            druidDataSource.setUrl(URL);
            druidDataSource.setUrl(USER);
            druidDataSource.setPassword(PWD);

            druidDataSource.setMaxWait(5000);
            druidDataSource.setMinIdle(3);
            druidDataSource.setMaxActive(10);
*/
            return dataSource.getConnection();

            /*1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);*/
        } catch (/*ClassNotFoundException | */SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConn() {
        //返回本地线程中的连接对象，如果不存在则新建一个
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = createConn();
            threadLocal.set(conn);
        }
        return conn;
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            return;
        }
        if (!conn.isClosed()) {
            conn.close();
            threadLocal.set(null);
        }
    }
}
