/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.AccountAdminManager;
import java.util.Scanner;

/**
 *
 * @author nakyumdepzaii
 */
public class AdminMenu {
    
    private AccountAdminManager aa;
    
    public AdminMenu(){
        aa = new AccountAdminManager();
    }

    public void AdminMenu() {
        Scanner input = new Scanner(System.in);
        int choice = 1;
        while (true) {
            try {
                do {
                    System.out.println("DEV MENU:");
                    System.out.println("1-Update Profile");
                    System.out.println("2-View User Expense Info");
                    System.out.println("3-Log out");
                    System.out.println("Enter your choice: ");
                    choice = Integer.parseInt(input.nextLine());
                    if (choice > 3 || choice < 1) {
                        System.err.println("Enter your choice from 1 - 8!");
                    }
                } while (choice > 3 || choice < 1);
                switch (choice) {
                    case 1:
                        aa.updateAdminProfle();
                        break;
                    case 2:
                        aa.viewUserExpenseInfo();
                        break;
                    case 3:
                        aa.logOut();
                        break;                
                }
            } catch (NumberFormatException e) {
                System.err.println("Enter a valid number!");
            }
        }

    }
}
