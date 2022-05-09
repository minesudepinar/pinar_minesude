package com.example.userloginsystem;

import java.sql.*;

public class DatabaseConnect {
    private static String user_name = "root";
    private static String password = "123456";

    static private Connection connection;
    public static User user;
    static private Statement statement;
    static {
        try {
            Statement s = DriverManager.getConnection("jdbc:mysql://localhost/?user="+user_name+"&password="+password).createStatement();
            s.executeUpdate("CREATE DATABASE if not exists userlogin;");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userlogin",user_name,password);
            statement = connection.createStatement();

            statement.execute("CREATE TABLE if not exists`userlogin`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `password` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,\n" +
                    "  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);\n");

            statement.execute("CREATE TABLE if not exists `userlogin`.`user_info` (\n" +
                    "  `user_id` INT NOT NULL,\n" +
                    "  `user_phone` VARCHAR(45) NULL,\n" +
                    "  `user_email` VARCHAR(45) NULL,\n" +
                    "  `user_address` VARCHAR(45) NULL,\n" +
                    "  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC));");


            System.out.println("Connected to the DB...");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    static public void addUser(String username, String password) throws Exception{
        PreparedStatement pstmt = connection.prepareStatement("insert into users (name,password) values(?,?)");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.executeUpdate();

        logIn(username,password);
    }

    static public User logIn(String username, String password) throws Exception{
        PreparedStatement pstmt = connection.prepareStatement("select * from users where name = ? and password = ?");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()){
            user = new User(resultSet.getInt("id"),resultSet.getString("name"));
        }

        loadUserData(user);

        return user;
    }

    private static void loadUserData(User user) throws Exception{
        if(user == null) return;
        PreparedStatement pstmt = connection.prepareStatement("select * from user_info where user_id = ?");
        pstmt.setInt(1, user.getId());
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()){
            user.setPhone(resultSet.getString("user_phone"));
            user.setAddress(resultSet.getString("user_address"));
            user.setEmail(resultSet.getString("user_email"));
            user.setInfoAdded(true);
        }

    }

    public static void saveUserInfo(String email, String phone, String address) throws Exception{
        PreparedStatement pstmt = connection.prepareStatement("insert into user_info (user_id,user_phone, user_email, user_address) values(?,?,?,?)");
        pstmt.setInt(1, user.getId());
        pstmt.setString(2, phone);
        pstmt.setString(3, email);
        pstmt.setString(4, address);
        pstmt.executeUpdate();
    }

    public static void updateUserInfo(String email, String phone, String address) throws Exception{
        PreparedStatement pstmt = connection.prepareStatement("update user_info set user_email = ?, user_phone = ?, user_address = ? where user_id = ?");
        pstmt.setString(1, email);
        pstmt.setString(2, phone);
        pstmt.setString(3, address);
        pstmt.setInt(4, user.getId());
        pstmt.executeUpdate();
    }

    public static User getUser() {
        return user;
    }
}
