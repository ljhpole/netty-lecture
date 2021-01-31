package com.classloader;

import java.sql.*;

/*
Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
1）class:com.mysql.jdbc.Driver的加载->java.sql.DriverManager的加载 -->
    static {
        loadInitialDrivers(); // --> 通过 System.getProperty("jdbc.drivers");
        println("JDBC DriverManager initialized");
    }

Driver Imple 加载2次,需要判断是否同一个命名空间的类加载器加载，避免相同类名的转换失败问题。


public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://127.0.0.1:3306/mydb";
        String username = "root";
        String password = "redhat";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM msg";
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        ResultSet resultSet = prepareStatement.executeQuery();
        resultSet.next();
        String address = resultSet.getString("address");
        System.out.println(address);
 */
public class mytestDbDriver {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://192.168.206.100:3306/topic_db?useUnicode=true&characterEncoding=UTF-8";
            String username = "root";
            String password = "123456";
            Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
            try {
                Connection connection = DriverManager.getConnection(url,username,password);
                String sql = "SELECT unique_node_id as UniqueId,node_id as ExternalId FROM t_topic_dict_node_relation_info";
                PreparedStatement prepareStatement = connection.prepareStatement(sql);
                ResultSet resultSet = prepareStatement.executeQuery();
                resultSet.next();
                String address = resultSet.getString("ExternalId");
                System.out.println(address);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
