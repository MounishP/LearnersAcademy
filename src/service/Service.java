package service;

import dao.DatabaseOperation;
import java.sql.SQLException;
import java.util.Scanner;

public class Service {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu() throws SQLException {
        System.out.println("Select your operation" + "\n" +
                "1. Add subject" + "\n" +
                "2. Add teacher" +"\n" +
                "3. Add class" +"\n" +
                "4. Assign classes for subjects" +"\n" +
                "5. Assign teachers for class for a subject" +"\n" +
                "6. List of students" +"\n" +
                "7. Class report");
        System.out.print("Enter you choice: ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                addSubject();
                break;
            case 2:
                addTeacher();
                break;
            case 3:
                addClass();
                break;
            case 4:
                assignClassToSubject();
                break;
            case 5:
                assignTeacherToClassForSubject();
                break;
            case 6:
                listOfStudents();
                break;
            case 7:
                classReport();
                break;
            default:
                break;
        }
    }

    private static void addClass() throws SQLException {
        System.out.print("Enter class: ");
        String clas = scanner.next();
        DatabaseOperation.addClassDB(clas);
    }

    private static void assignClassToSubject() {
        System.out.println("assign class to subject");
    }

    private static void assignTeacherToClassForSubject() {
        System.out.println("assign teacher to class for subject");
    }

    private static void listOfStudents() {
        System.out.println("list of students");
    }

    private static void classReport() {
        System.out.println("class report");
    }

    private static void addTeacher() throws SQLException {
        System.out.print("Enter teacher name: ");
        String teacher = scanner.next();
        DatabaseOperation.addTeacherDB(teacher);
    }

    private static void addSubject() throws SQLException {
        System.out.print("Enter subject: ");
        String subject = scanner.next();
        DatabaseOperation.addSubjectDB(subject);
    }

    public static void adminLogin() throws SQLException {
        System.out.println("---------------Login-------------");
        System.out.print("Enter the admin user name: ");
        String adminUser = scanner.next();
        System.out.print("Enter the admin password: ");
        String adminPass = scanner.next();
        new DatabaseOperation(adminUser, adminPass);
    }
}
