/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LoginManager;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author nakyumdepzaii
 */
public class LoginMenu {

    private LoginManager lm;
    private AdminMenu am;
    private UserMenu us;

    public LoginMenu() throws IOException {
        lm = new LoginManager();
        am = new AdminMenu();
        us = new UserMenu();
    }

    public void LoginMenu() throws IOException {
        Scanner input = new Scanner(System.in);
        boolean check = true;
        do {
            System.out.println("Welcome to Expense Sharing Management System");
            System.out.print("Enter username: ");
            String username = input.nextLine();
            System.out.print("Enter password: ");
            String password = input.nextLine();
            if (lm.checkLogin(username, password)) {
                System.out.println("Login successfully!");
                if(lm.checkAdmin(username)){
                    am.AdminMenu();
                } else{
                    us.UserMenu();
                }
                check = false;
            } else{
                System.out.println("Incorrect username or password! Please enter again!");
            }
        }while(check);
    }
}
