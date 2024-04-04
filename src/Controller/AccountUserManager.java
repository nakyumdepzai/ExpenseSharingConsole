/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.LoginMenu;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author nakyumdepzaii
 */
public class AccountUserManager {

    public AccountUserManager() {

    }

    public void updateUserProfile() {

    }

    public void logOut() throws IOException {
        System.out.println("Are you sure to log out?");
        System.out.println("'yes' to log out | 'no' to stay");
        Scanner input = new Scanner(System.in);
        String n = input.nextLine();
        if (n.equals("yes")) {
            System.out.println("Log out successfully!");
            LoginMenu lm = new LoginMenu();
            lm.LoginMenu();
        }
    }
}
