package org.zerock;

public class Admin {
    private final String adminPassword = "admin123";

    public boolean checkAdmin(String password) {
        return  adminPassword.equals(password);
    }
}
