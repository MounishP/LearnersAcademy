package dao;

import service.Service;

import java.sql.*;

public class DatabaseOperation {
    private static Connection con;

    public DatabaseOperation(String adminUser, String adminPass) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/learnersacademy";
        String username = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            checkAdmin(adminUser, adminPass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addClassDB(String cal) throws SQLException {
        int i;
        String id = "select id from class";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(id);
        if (resultSet.next()) {
            i = resultSet.getInt("id");
        } else {
            i = 0;
        }
        PreparedStatement preparedStatement = con.prepareStatement("insert into class values(?,?)");
        preparedStatement.setInt(1, i+1);
        preparedStatement.setString(2,cal);
        int ps = preparedStatement.executeUpdate();
        System.out.println(ps + " records inserted");
    }

    public static void addSubjectDB(String subject) throws SQLException {
        int i;
        String id = "select id from subjects";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(id);
        if (resultSet.next()) {
            i = resultSet.getInt("id");
        } else {
            i = 0;
        }
        PreparedStatement preparedStatement = con.prepareStatement("insert into subjects values(?,?)");
        preparedStatement.setInt(1, i+1);
        preparedStatement.setString(2,subject);
        int ps = preparedStatement.executeUpdate();
        System.out.println(ps + " records inserted");
    }

    public static void addTeacherDB(String teacher) throws SQLException {
        int i;
        String id = "select id from teachers";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(id);
        if (resultSet.next()) {
            i = resultSet.getInt("id");
        } else {
            i = 0;
        }
        PreparedStatement preparedStatement = con.prepareStatement("insert into teachers values(?,?)");
        preparedStatement.setInt(1, i+1);
        preparedStatement.setString(2,teacher);
        int ps = preparedStatement.executeUpdate();
        System.out.println(ps + " records inserted");
    }

    private void checkAdmin(String adminUser, String adminPass) throws SQLException {
        String query = "select * from admin";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        String username = resultSet.getString("userName");
        String password = resultSet.getString("userPass");
        if (username.equals(adminUser) && password.equals(adminPass)) {
            System.out.println("Admin login successful");
            Service.menu();
        } else {
            System.out.println("Admin login failed");
        }
    }
}
