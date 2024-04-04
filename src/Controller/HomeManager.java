/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pair;
import Model.Person;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nakyumdepzaii
 */
public class HomeManager {

    Scanner input = new Scanner(System.in);
    int n;
    int n1 = 0;
    int n2 = 0;
    ArrayList<Person> p;
    ArrayList<Pair> pair;
    ArrayList<Person> plon;
    ArrayList<Person> pbe;
    double sumcung = 0;
    double summem = 0;
    double sumflex = 0;
    double sumno = 0;

    public void nhapSoLuong(int n) {
        this.n = n;
        p = new ArrayList<>();
        plon = new ArrayList<>();
        pbe = new ArrayList<>();
        pair = new ArrayList<>();
    }

    public HomeManager() {

    }

    public void nhapThanhVien(int n) {
        System.out.println("Enter member (" + n + ")");
        //Nhap ten thanh vien
        for (int i = 0; i < n; ++i) {
            System.out.print("Member " + (i + 1) + ": ");
            String name = input.nextLine();
            p.add(new Person(name)); //them thanh vien vao mang p
        }
    }

    public void nhapTienCung() {//tien cung la tien co dinh(chia deu cho tat ca thanh vien)
        System.out.print("Enter fixed expense: ");
        System.out.println("Fixed expense is the expense that will be equally shared for every member!");
        System.out.println("Enter 'quit' to move on to the next session!");
        while (true) {
            String nhaptiencung = input.nextLine();
            if (nhaptiencung.equalsIgnoreCase("quit")) {
                break;
            } else {
                try {
                    double parsetiencung = Double.parseDouble(nhaptiencung);
                    sumcung += parsetiencung;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter quit or a valid number");
                }
            }
        }
        for (int i = 0; i < n; i++) {// set tien cung cho tung thanh vien
            p.get(i).setCung(sumcung * 1.0 / n);
        }
    }

    public void nhapTienMem() {//tien mem la tien cua tung thanh vien phai tra
        System.out.println("Enter flexible expense: ");
        System.out.println("Flexible expense is the expense that depends on each member!");
        System.out.println("For example: Only 'Khanh' has motorbike, so only him has flexible expense is 150.000 VND paying for motorbike parking!");
        System.out.println("If member doesn't has any flexible expense, enter 0!");
        System.out.println("Enter 'next' to move on to the next member!");
        for (int i = 0; i < n; i++) {
            double sum = 0;
            System.out.print("Flexible expense of " + p.get(i).getName() + " : ");
            while (true) {
                String nhaptienmem = input.nextLine();
                if (nhaptienmem.equalsIgnoreCase("next")) {
                    break;
                } else {
                    try {
                        double parsetienmem = Double.parseDouble(nhaptienmem);
                        sum += parsetienmem;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter next or a valid number");
                    }
                }
            }
            summem += sum;//tong tien mem
            p.get(i).setMem(sum); //set tien mem cho tung thanh vien
        }
    }

    public void nhapTienFlex() {//tien flex la tien can chia deu cho tung thanh vien trong can ho
        System.out.println("Enter shared expense: ");
        System.out.println("Shared expense is the expense of each member that he/she spends to contribute to the living activities that need to be shared equally for each member!");
        System.out.println("Enter 'next' to move on to the next member!");
        for (int i = 0; i < n; i++) {
            double sum = 0;
            System.out.print("Shared expense of " + p.get(i).getName() + ": ");
            while (true) {
                String nhaptienflex = input.nextLine();
                if (nhaptienflex.equalsIgnoreCase("next")) {
                    break;
                } else {
                    try {
                        double parsetienflex = Double.parseDouble(nhaptienflex);
                        sum += parsetienflex;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter next or a valid number");
                    }
                }
            }
            sumflex += sum;//tong tien flex
            p.get(i).setFlex(sum);
        }
        for (int i = 0; i < n; i++) {// set su chenh lech cho tung thanh vien de tinh toan
            p.get(i).setDiff(-sumflex * 1.0 / n + p.get(i).getFlex());
        }
    }

    public void chia2Mang() {//chia 2 mang de tinh toan
        for (int i = 0; i < n; i++) {
            if (p.get(i).getDiff() > 0) {
                plon.add(p.get(i)); //plon bao gom nhung thanh vien duoc nhan tien
                n1++;
            } else if (p.get(i).getDiff() < 0) {
                pbe.add(p.get(i));//pbe bao gom nhung thanh vien phai tra tien
                n2++;
            }
        }
        while (n1 != n2) {//truong hop 2 mang chenh lech do so nguoi le
            if (n1 > n2) {
                pbe.add(new Person("newname", false, 0, 0, 0, 0));
                n2++;
            } else if (n1 < n2) {
                plon.add(new Person("newname", false, 0, 0, 0, 0));
                n1++;
            }
        }
    }

    public void checkAdmin() {//admin se la nguoi chuyen khoan thanh toan cac khoan chi tieu
        int count = 0;
        boolean check = true;
        System.out.println("Enter Admin's name: ");
        System.out.println("Admin will responsible for expense paying!");
        do {
            String admin = input.nextLine();
            int i;
            for (i = 0; i < n; i++) {
                if (admin.equalsIgnoreCase(p.get(i).getName())) {
                    p.get(i).setAdmin(true);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Member doesn't exist! Please enter correct member's name!");
            } else {
                check = false;
            }
        } while (check);
    }

    public void setPair(int n) {//tao nC2 cap voi so tien no nhat dinh
        pair.ensureCapacity(n * (n - 1) / 2);
        int a = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a < pair.size()) {
                    pair.get(a).setPerson1(p.get(i));
                    pair.get(a).setPerson2(p.get(j));
                } else {
                    pair.add(new Pair(p.get(i), p.get(j), 0.0));
                }
                a++;
            }
        }
        System.out.println("Enter pair's debt");
        System.out.println("If it is a correct direction, enter positive numbers ");
        System.out.println("Else, enter negative numbers");
        System.out.println("Enter 'next' to move on to the next pair!");
        for (int i = 0; i < pair.size(); i++) {//tien no la tien no
            System.out.print(pair.get(i).getPerson1().getName() + " owe " + pair.get(i).getPerson2().getName() + ": ");
            double sum = 0;
            while (true) {
                String nhaptienno = input.nextLine();
                if (nhaptienno.equalsIgnoreCase("next")) {
                    break;
                } else {
                    try {
                        double parsetienno = Double.parseDouble(nhaptienno);
                        sum += parsetienno;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter next or a valid number");
                    }
                }
            }
            pair.get(i).setTienno(sum);
        }
    }

    public void setCungSetMem() {//set tien cung tien mem cho tung cap ---- thu tu cap nhu sau Person1 Person 2 tienno <=> Person1 no Person2: tienno => neu tien no < 0 => Person2 no Person1: Math.abs(tienno)
        for (int i = 0; i < pair.size(); i++) {
            if (pair.get(i).getPerson1().isAdmin()) {//neu admin la Person1
                pair.get(i).setTienno(-pair.get(i).getPerson2().getCung() - pair.get(i).getPerson2().getMem() + pair.get(i).getTienno());
            } else if (pair.get(i).getPerson2().isAdmin()) {//neu admin la Person2
                pair.get(i).setTienno(pair.get(i).getPerson1().getCung() + pair.get(i).getPerson1().getMem() + pair.get(i).getTienno());
            }
        }
    }

    public void tinhTien() {//tinh tien se cho ra ket qua no giua tat ca cac cap bao gom: tien cung, tien mem, tien flex va tien no
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (plon.get(j).getDiff() <= Math.abs(pbe.get(i).getDiff())) {//check neu 1 trong 2 nguoi trong cap dang xet co so tien check lech = 0 se skip
                    if (plon.get(j).getDiff() == 0 || pbe.get(i).getDiff() == 0) {
                        continue;
                    }
                    for (int k = 0; k < pair.size(); k++) {
                        if (pbe.get(i).equals(pair.get(k).getPerson1())) {//chieu thuan
                            if (plon.get(j).equals(pair.get(k).getPerson2())) {//check cap dang xet = cap trong mang pair
                                pair.get(k).setTienno(plon.get(j).getDiff() + pair.get(k).getTienno());//set tien no + them tien check lech 
                                break;
                            }
                        } else if (plon.get(j).equals(pair.get(k).getPerson1())) {//chieu nguoc 
                            if (pbe.get(i).equals(pair.get(k).getPerson2())) {//check cap dang xet = cap trong mang pair
                                pair.get(k).setTienno(pbe.get(i).getDiff() + pair.get(k).getTienno());//set tien no + them tien check lech 
                                break;
                            }
                        }
                    }
                    pbe.get(i).setDiff(pbe.get(i).getDiff() + plon.get(j).getDiff());
                    plon.get(j).setDiff(0);
                } else {
                    if (plon.get(j).getDiff() == 0 || pbe.get(i).getDiff() == 0) {
                        continue;
                    }
                    for (int k = 0; k < pair.size(); k++) {
                        if (pbe.get(i).equals(pair.get(k).getPerson1())) {//chieu thuan 
                            if (plon.get(j).equals(pair.get(k).getPerson2())) {
                                pair.get(k).setTienno(Math.abs(pbe.get(i).getDiff()) + pair.get(k).getTienno());
                                break;
                            }
                        } else if (plon.get(j).equals(pair.get(k).getPerson1())) {//chieu nguoc
                            if (pbe.get(i).equals(pair.get(k).getPerson2())) {
                                pair.get(k).setTienno(pbe.get(i).getDiff() + pair.get(k).getTienno());
                                break;
                            }
                        }
                    }
                    plon.get(j).setDiff(pbe.get(i).getDiff() + plon.get(j).getDiff());
                    pbe.get(i).setDiff(0);
                }
            }
        }
        for (int i = 0; i < pair.size(); i++) {
            if (pair.get(i).getTienno() > 0) {
                System.out.println(pair.get(i).getPerson1().getName() + " will pay " + pair.get(i).getPerson2().getName() + ": " + pair.get(i).getTienno() + " VND");
            } else if (pair.get(i).getTienno() < 0) {
                System.out.println(pair.get(i).getPerson2().getName() + " will pay " + pair.get(i).getPerson1().getName() + ": " + Math.abs(pair.get(i).getTienno()) + " VND");
            }
        }
        System.out.println("Admin pay for fixed expense: " + sumcung + " VND");
        System.out.println("Admin pay for flexible expense: " + summem + " VND");
    }
}
