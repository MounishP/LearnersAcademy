package report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DetailReport {
    public static void getReport(Connection connection) throws SQLException {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select your operation" + "\n" +
                "1. Academy Report" + "\n" +
                "2. Student Report");
        System.out.print("Enter you choice: ");
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Enter correct choice");
        }
        switch (choice) {
            case 1:
                academyReport(connection);
                break;
            case 2:
                studentReport(connection);
                break;
            default:
                System.out.println("Invalid input");
        }

    }

    private static void studentReport(Connection connection) throws SQLException {
        String sql = "select * from students";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("ID - Class - Student");
        System.out.println("------------------------------");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String classes = resultSet.getString("classes");
            String studentName = resultSet.getString("studentName");
            System.out.format("%s,%s,%s",id,classes,studentName);
            System.out.println();
        }
    }

    private static void academyReport(Connection connection) throws SQLException {
        String sql = "select * from academy";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("ID - Class - Subject - Teacher");
        System.out.println("------------------------------");
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String classes = resultSet.getString("classes");
            String subjects = resultSet.getString("subjects");
            String teachers = resultSet.getString("teachers");
            System.out.format("%s,%s,%s,%s",id,classes,subjects,teachers);
            System.out.println();
        }
    }
}
