package service;

import dao.DatabaseOperation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Service {
    static int choice;
    private static Scanner scanner = new Scanner(System.in);
    private static List cls = classess();
    private static List subs = subjects();
    private static List teachs = teachers();

    private static List<String> classess() {
        List<String> classes = new ArrayList<>();
        classes.add("One");
        classes.add("second");
        classes.add("third");
        classes.add("fourth");
        classes.add("fifth");
        classes.add("sixth");
        classes.add("seventh");
        classes.add("eighth");
        classes.add("ninth");
        classes.add("tenth");
        return classes;
    }

    private static List<String> subjects() {
        List<String> subjects = new ArrayList<>();
        subjects.add("Kannada");
        subjects.add("English");
        subjects.add("Hindi");
        subjects.add("Maths");
        subjects.add("Science");
        subjects.add("Social");
        return subjects;
    }

    private static List<String> teachers() {
        List<String> teachers = new ArrayList<>();
        teachers.add("Mounish");
        teachers.add("Ravali");
        teachers.add("Ratnam");
        teachers.add("Chandrakala");
        teachers.add("Madhavi");
        return teachers;
    }

    public static void menu() throws SQLException {
        System.out.println("Select your operation" + "\n" +
                "1. Assign subjects to classes" + "\n" +
                "2. Assign Teacher to class and subject");
        System.out.print("Enter you choice: ");
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Enter correct choice");
        }
        switch (choice) {
            case 1:
                assignSubjectToClass();
                break;
            case 2:
                assignTeacherToClassSubject();
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    private static void assignTeacherToClassSubject() throws SQLException {
        System.out.print("Enter class: ");
        String clas = scanner.next();
        for (Object cl : cls) {
            if (clas.equalsIgnoreCase((String) cl)) {
                System.out.print("Enter subject: ");
                String subject = scanner.next();
                for (Object sub : subs) {
                    if (subject.equalsIgnoreCase((String) sub)) {
                        System.out.print("Enter the teacher name: ");
                        String teacher = scanner.next();
                        for (Object teach : teachs) {
                            if (teacher.equalsIgnoreCase((String) teach)) {
                                DatabaseOperation.dbAssignTeachertoClassSub(clas, subject, teacher);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void assignSubjectToClass(){
        System.out.print("Enter the class to assign subject: ");
        String clas = scanner.next();
        for (Object cl : cls) {
            if (clas.equalsIgnoreCase((String) cl)) {
                System.out.print("Enter subject: ");
                String sub = scanner.next();
                for (Object sb : subs) {
                    if (sub.equalsIgnoreCase((String) sb)) {
                        DatabaseOperation.dbAssignSubjectClass(clas, sub);
                    }
                }
            }
        }
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

