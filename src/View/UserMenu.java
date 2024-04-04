/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.AccountUserManager;
import Controller.HomeManager;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author nakyumdepzaii
 */
public class UserMenu {

    private final HomeManager hm;
    private final AccountUserManager au;

    public UserMenu() {
        hm = new HomeManager();
        au = new AccountUserManager();
    }

    public void UserMenu() throws IOException {
        Scanner input = new Scanner(System.in);
        int choice = 1;
        while (true) {
            try {
                do {
                    System.out.println("USER MENU:");
                    System.out.println("1-Update Profile");
                    System.out.println("2-Expense Calculate");
                    System.out.println("3-Log out");
                    System.out.print("Enter your choice: ");
                    choice = Integer.parseInt(input.nextLine());
                    if (choice > 3 || choice < 1) {
                        System.err.println("Enter your choice from 1 - 8!");
                    }
                } while (choice > 3 || choice < 1);

                switch (choice) {
                    case 1:
                        au.updateUserProfile();
                        break;
                    case 2:
                        System.out.print("Nhap so nguoi: ");
                        int n = input.nextInt();
                        input.nextLine();
                        hm.nhapSoLuong(n);
                        hm.nhapThanhVien(n);
                        hm.setPair(n);
                        hm.nhapTienCung();
                        hm.nhapTienMem();
                        hm.nhapTienFlex();
                        hm.checkAdmin();
                        hm.setCungSetMem();
                        hm.chia2Mang();
                        hm.tinhTien();
                        break;
                    case 3:
                        au.logOut();
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Enter a valid number!");
            }
        }

    }
}