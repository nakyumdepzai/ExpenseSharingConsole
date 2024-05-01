/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pair;
import Model.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author nakyumdepzaii
 */
public class HomeManager {

    Scanner input = new Scanner(System.in);
    int n;
    int n1;
    int n2;
    ArrayList<Person> p;
    ArrayList<Pair> pair;
    ArrayList<Person> plon;
    ArrayList<Person> pbe;

    public HomeManager() {

    }

    public void nhapSoLuong(int n) {
        this.n = n;
        p = new ArrayList<>();
        plon = new ArrayList<>();
        pbe = new ArrayList<>();
        pair = new ArrayList<>();
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
//        System.out.println("Enter pair's debt");
//        System.out.println("If it is a correct direction, enter positive numbers ");
//        System.out.println("Else, enter negative numbers");
//        System.out.println("Enter 'next' to move on to the next pair!");
//        for (int i = 0; i < pair.size(); i++) {//tien no la tien no
//            System.out.print(pair.get(i).getPerson1().getName() + " owe " + pair.get(i).getPerson2().getName() + ": ");
//            double sum = 0;
//            while (true) {
//                String nhaptienno = input.nextLine();
//                if (nhaptienno.equalsIgnoreCase("next")) {
//                    break;
//                } else {
//                    try {
//                        double parsetienno = Double.parseDouble(nhaptienno);
//                        sum += parsetienno;
//                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid input! Please enter next or a valid number");
//                    }
//                }
//            }
//            pair.get(i).setTienno(sum);
//        }
    }

    public void nhapTienCung() {//tien cung la tien co dinh(chia deu cho tat ca thanh vien)
        double sumcung = 0;
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
        double summem = 0;
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

    public void nhapTienFlex(int nSub, ArrayList<Person> pSub) {//tien flex la tien can chia deu cho tung thanh vien trong can ho
        double sumflex = 0;
        System.out.println("Enter shared expense: ");
        System.out.println("Shared expense is the expense of each member that he/she spends to contribute to the living activities that need to be shared equally for each member!");
        System.out.println("Enter 'next' to move on to the next member!");
        for (int i = 0; i < nSub; i++) {
            double sum = 0;
            System.out.print("Shared expense of " + pSub.get(i).getName() + ": ");
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
            pSub.get(i).setFlex(sum);
        }
        for (int i = 0; i < nSub; i++) {// set su chenh lech cho tung thanh vien de tinh toan
            pSub.get(i).setDiff(-sumflex * 1.0 / nSub + pSub.get(i).getFlex());
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

    public void setCungSetMem(ArrayList<Pair> pairSub) {//set tien cung tien mem cho tung cap ---- thu tu cap nhu sau Person1 Person 2 tienno <=> Person1 no Person2: tienno => neu tien no < 0 => Person2 no Person1: Math.abs(tienno)
        for (int i = 0; i < pairSub.size(); i++) {
            if (pairSub.get(i).getPerson1().isAdmin()) {//neu admin la Person1
                pairSub.get(i).setTienno(-pairSub.get(i).getPerson2().getCung() - pairSub.get(i).getPerson2().getMem() + pairSub.get(i).getTienno());
            } else if (pairSub.get(i).getPerson2().isAdmin()) {//neu admin la Person2
                pairSub.get(i).setTienno(pairSub.get(i).getPerson1().getCung() + pairSub.get(i).getPerson1().getMem() + pairSub.get(i).getTienno());
            }
        }
    }

    public void chia2Mang(int nSub, ArrayList<Person> pSub, ArrayList<Person> plonSub, ArrayList<Person> pbeSub) {//chia 2 mang de tinh toan
        n1 = 0;
        n2 = 0;
        for (int i = 0; i < nSub; i++) {
            if (pSub.get(i).getDiff() > 0) {
                plonSub.add(pSub.get(i)); //plon bao gom nhung thanh vien duoc nhan tien
                n1++;
            } else if (pSub.get(i).getDiff() < 0) {
                pbeSub.add(pSub.get(i));//pbe bao gom nhung thanh vien phai tra tien
                n2++;
            }
        }
        while (n1 != n2) {//truong hop 2 mang chenh lech do so nguoi le
            if (n1 > n2) {
                pbeSub.add(new Person("newname", false, 0, 0, 0, 0));
                n2++;
            } else if (n1 < n2) {
                plonSub.add(new Person("newname", false, 0, 0, 0, 0));
                n1++;
            }
        }
        Collections.sort(pbeSub, (Person p1, Person p2) -> Double.compare(p2.getDiff(), p1.getDiff()));
        Collections.sort(plonSub, (Person p1, Person p2) -> Double.compare(p2.getDiff(), p1.getDiff()));
        for (int i = 0; i < n2; i++) {
            System.out.println(pbeSub.get(i).getName() + "-" + pbeSub.get(i).getDiff());
        }
        for (int i = 0; i < n1; i++) {
            System.out.println(plonSub.get(i).getName() + "-" + plonSub.get(i).getDiff());
        }

    }

    public void setPairSub(int nSub, ArrayList<Pair> pairSub, ArrayList<Person> pSub) {//tao nC2 cap voi so tien no nhat dinh
        pairSub = new ArrayList<>();
        pSub = new ArrayList<>();
        pairSub.ensureCapacity(nSub * (nSub - 1) / 2);
        int a = 0;
        for (int i = 0; i < nSub - 1; i++) {
            for (int j = i + 1; j < nSub; j++) {
                if (a < pairSub.size()) {
                    pairSub.get(a).setPerson1(pSub.get(i));
                    pairSub.get(a).setPerson2(pSub.get(j));
                } else {
                    pairSub.add(new Pair(pSub.get(i), pSub.get(j), 0.0));
                }
                a++;
            }
        }
    }

    public void tinhTurn() {
        ArrayList<Person> pSub;
        int nSub;
        while (true) {
            pSub = new ArrayList<>();
            nSub = 0;
            System.out.print("Nhap so nguoi se tinh luot nay: ");
            String nSubString = input.nextLine();
            if (nSubString.equalsIgnoreCase("quit")) {
                break;
            }
            try {
                nSub = Integer.parseInt(nSubString);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter next or a valid number");
            }
            System.out.println("Enter order number (" + nSub + ")");
            System.out.println("Please enter in ascending order!");
            for (int i = 0; i < nSub; ++i) {
                System.out.print("Member: ");
                int num = input.nextInt();
                Person personFromP = p.get(num - 1);
                Person newPerson = new Person(personFromP.getName());
                pSub.add(newPerson);
            }

            input.nextLine();
            nhapTienFlex(nSub, pSub);
                for (Person psub : pSub) {
                    for (int i = 0; i < p.size(); i++) {
                        if (p.get(i).getName().equals(psub.getName())) {
                            p.get(i).setDiff(p.get(i).getDiff() + psub.getDiff());
                        }
                    }
                }
            }
        setCungSetMem(pair);
        chia2Mang(n, p, plon, pbe);
        tinhTien(plon, pbe, pair);
        result(pair);
    }

    public void tinhTien(ArrayList<Person> plonSub, ArrayList<Person> pbeSub, ArrayList<Pair> pairSub) {//tinh tien se cho ra ket qua no giua tat ca cac cap bao gom: tien cung, tien mem, tien flex va tien no
        for (int j = 0; j < n2; j++) {
            for (int i = 0; i < n1; i++) {
                if (plonSub.get(j).getDiff() <= Math.abs(pbeSub.get(i).getDiff())) {//check neu 1 trong 2 nguoi trong cap dang xet co so tien check lech = 0 se skip
                    if (plonSub.get(j).getDiff() == 0 || pbeSub.get(i).getDiff() == 0) {
                        continue;
                    }
                    for (int k = 0; k < pairSub.size(); k++) {
                        if (pbeSub.get(i).equals(pairSub.get(k).getPerson1())) {//chieu thuan
                            if (plonSub.get(j).equals(pairSub.get(k).getPerson2())) {//check cap dang xet = cap trong mang pair
                                pairSub.get(k).setTienno(plonSub.get(j).getDiff() + pairSub.get(k).getTienno());//set tien no + them tien check lech 
                                break;
                            }
                        } else if (plonSub.get(j).equals(pairSub.get(k).getPerson1())) {//chieu nguoc 
                            if (pbeSub.get(i).equals(pairSub.get(k).getPerson2())) {//check cap dang xet = cap trong mang pair
                                //pairSub.get(k).setTienno(pbeSub.get(i).getDiff() + pairSub.get(k).getTienno());//set tien no + them tien check lech 
                                pairSub.get(k).setTienno(-plonSub.get(j).getDiff() + pairSub.get(k).getTienno());//set tien no + them tien check lech 
                                break;
                            }
                        }
                    }
                    pbeSub.get(i).setDiff(pbeSub.get(i).getDiff() + plonSub.get(j).getDiff());
                    plonSub.get(j).setDiff(0);
                    System.out.println(pbeSub.get(i).getName() + "_" + pbeSub.get(i).getDiff());

                } else {
                    if (plonSub.get(j).getDiff() == 0 || pbeSub.get(i).getDiff() == 0) {
                        continue;
                    }
                    for (int k = 0; k < pairSub.size(); k++) {
                        if (pbeSub.get(i).equals(pairSub.get(k).getPerson1())) {//chieu thuan 
                            if (plonSub.get(j).equals(pairSub.get(k).getPerson2())) {
                                pairSub.get(k).setTienno(Math.abs(pbeSub.get(i).getDiff()) + pairSub.get(k).getTienno());
                                break;
                            }
                        } else if (plonSub.get(j).equals(pairSub.get(k).getPerson1())) {//chieu nguoc
                            if (pbeSub.get(i).equals(pairSub.get(k).getPerson2())) {
                                pairSub.get(k).setTienno(pbeSub.get(i).getDiff() + pairSub.get(k).getTienno());
                                break;
                            }
                        }
                    }
                    plonSub.get(j).setDiff(pbeSub.get(i).getDiff() + plonSub.get(j).getDiff());
                    pbeSub.get(i).setDiff(0);
                    System.out.println(plonSub.get(j).getName() + "_" + plonSub.get(j).getDiff());
                }
            }
        }
    }

    public void result(ArrayList<Pair> pairSub) {
        for (int i = 0; i < pairSub.size(); i++) {
            if (pairSub.get(i).getTienno() > 0) {
                System.out.printf("%s will pay %s: %.2f VND",
                        pairSub.get(i).getPerson1().getName(),
                        pairSub.get(i).getPerson2().getName(),
                        pairSub.get(i).getTienno());
                System.out.println("");
            } else if (pairSub.get(i).getTienno() < 0) {
                System.out.printf("%s will pay %s: %.2f VND",
                        pairSub.get(i).getPerson2().getName(),
                        pairSub.get(i).getPerson1().getName(),
                        Math.abs(pairSub.get(i).getTienno()));
                System.out.println("");
            }
        }
    }
}
