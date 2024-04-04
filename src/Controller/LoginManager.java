/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Account;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author nakyumdepzaii
 */
public class LoginManager {

    private final ArrayList<Account> accountList;
    private final DBHelper dbhelper;

    public LoginManager() throws IOException {
        dbhelper = new DBHelper();
        accountList = dbhelper.readFile();
    }

    public boolean checkLogin(String inputUsername, String inputPassword) {
        for (Account i : accountList) {
            if (i.getUsername().equals(inputUsername) && i.getPassword().equals(inputPassword)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkAdmin(String inputUsername) {
        for (Account i : accountList) {
            if (i.getUsername().equals(inputUsername) && "AD".equals(i.getRole())) {
                return true;
            }
        }
        return false;
    }
}
