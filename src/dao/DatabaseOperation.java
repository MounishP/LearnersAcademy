package dao;

import report.DetailReport;
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

    public static void dbAssignSubjectClass(String cl, String sb) {
        try {
            String query = "insert into academy values(?,?,?,?)";
            int id = DBvalidation.getId(con);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, cl.toUpperCase());
            preparedStatement.setString(3, sb.toUpperCase());
            preparedStatement.setString(4, null);
            preparedStatement.execute();
        } catch (SQLException sql) {
            System.out.println(sql);
        }
    }

    public static void dbAssignTeachertoClassSub(String clas, String subject, String teacher) {
        try {
            String query = String.format("update academy set teachers=\"%s\" where classes=\"%s\" and subjects = \"%s\";", teacher, clas, subject);
            Statement statement = con.createStatement();
            if (DBvalidation.checkTeacher(clas, subject, con)) {
                statement.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.out.println("Invalid SQL Syntax");
        }
    }

    public static void dbAssignStudentToClass(String student, String cls) throws SQLException {
        String query = "insert into students values(?,?,?)";
        int id = DBvalidation.getIdStudent(con);
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, cls);
        preparedStatement.setString(3, student);
        if (DBvalidation.checkStudent(student,cls,con)) {
            System.out.println(id);
            preparedStatement.execute();
        }
    }

    public static void getReport() throws SQLException {
        DetailReport.getReport(con);
    }


    private void checkAdmin(String adminUser, String adminPass) throws SQLException, ClassNotFoundException {
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
