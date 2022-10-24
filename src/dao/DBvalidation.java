package dao;

import java.sql.*;

class DBvalidation {

    static int getId(Connection connection) throws SQLException {
        String sql = "select id from academy";
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
            System.out.println(cls);
            String sub = resultSet.getString("subjects");
            if (clas.equalsIgnoreCase(cls) && sub.equalsIgnoreCase(subject)) {
                return true;
            }
        }
        return false;
    }
}
