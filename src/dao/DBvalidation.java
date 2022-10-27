package dao;

import java.sql.*;

class DBvalidation {

    static int getId(Connection connection) throws SQLException {
        String sql = "select id from academy ORDER BY id DESC LIMIT 1;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int i;
        if (!resultSet.next()) {
            i = 1;
        } else {
            i = resultSet.getInt(1);
            i = i + 1;
        }
        return i;
    }

    public static boolean checkTeacher(String clas, String subject, Connection con) throws SQLException {
        String sql = "select classes,subjects from academy";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String cls = resultSet.getString("classes");
//            System.out.println(cls);
            String sub = resultSet.getString("subjects");
            if (clas.equalsIgnoreCase(cls) && sub.equalsIgnoreCase(subject)) {
                return true;
            }
        }
        return false;
    }

    public static int getIdStudent(Connection con) throws SQLException {
        String sql = "select id from students ORDER BY id DESC LIMIT 1;";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int i;
        if (!resultSet.next()) {
            i = 1;
        } else {
            i = resultSet.getInt("id");
            i = i + 1;
        }
        return i;
    }

    static boolean checkStudent(String student, String cls, Connection con) throws SQLException {
        String sql = "select studentName from students;";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (!resultSet.next()) {
//            System.out.println("inside rs1");
            return true;
        } else {
            while (resultSet.next()) {
//                System.out.println("inside rs2");
                String st = resultSet.getString("studentName");
                if (st.equalsIgnoreCase(student)) {
                    return false;
                }
            }
//            System.out.println("outside while");
            return true;
        }
    }
}
