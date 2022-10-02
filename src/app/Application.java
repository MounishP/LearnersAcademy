package app;

import service.Service;

import java.sql.SQLException;


public class Application {
    public static void main(String[] args) throws SQLException {
        Service.adminLogin();
    }
}
