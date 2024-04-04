/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Account;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author nakyumdepzaii
 */
public class DBHelper {

    private ArrayList<Account> accountList;

    public DBHelper() {
        accountList = new ArrayList<>();
    }

    public ArrayList<Account> readFile() throws FileNotFoundException, IOException {

        File file = new File("src/Database/account.dat");

        if (!file.exists()) {
            System.out.println("File doesn't exist");
            return accountList; // Return an empty list or handle accordingly if the file doesn't exist
        }

        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                if (split.length >= 3) { // Ensure at least three parts in the line
                    String username = split[0].trim();
                    String password = split[1].trim();
                    String role = split[2].trim();
                    accountList.add(new Account(username, password, role));
                } else {
                    System.out.println("Invalid line format: " + line);
                    // Handle the case where the line doesn't have enough parts separated by commas
                    // You might want to log this or handle it differently based on your requirements
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception");
            // Handle this exception according to your application's logic
        }
        return accountList;
    }
}
