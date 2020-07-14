package com.igeek.jdbc;

public class JdbcTest {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载数据库驱动
            // 通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/order? characterEncoding=utf-8", "root", "root");
            String sql = "select from user where username = ?"; // 定义sql语句 ? 表示占位符
            preparedStatement = connection.prepareStatement(sql); // 获取预处理 statement
            // 设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "王五");
            resultSet = preparedStatement.executeQuery();// 向数据库发出sql执行查 询，查询出结果集
            while (resultSet.next()) { // 遍历查询结果集
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


