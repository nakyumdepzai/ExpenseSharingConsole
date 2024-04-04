/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.LoginMenu;
import java.io.IOException;

/**
 *
 * @author Khanh
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        LoginMenu lm = new LoginMenu();
        lm.LoginMenu();
//          UserMenu um = new UserMenu();
//          um.UserMenu();

    }
}
