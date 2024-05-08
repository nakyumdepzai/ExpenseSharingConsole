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
    ArrayList<Person> pPos;
    ArrayList<Person> pNeg;
    double sumFlexible = 0;
    double sumFixed = 0;

    public HomeManager() {

    }

    public void importMemberQuantity(int n) {
        this.n = n;
        p = new ArrayList<>();
        pPos = new ArrayList<>();
        pNeg = new ArrayList<>();
        pair = new ArrayList<>();
    }

    public void importMember(int n) {
        System.out.println("Enter member (" + n + ")");
        for (int i = 0; i < n; ++i) {
            System.out.print("Member " + (i + 1) + ": ");
            String name = input.nextLine();
            p.add(new Person(name)); 
        }
    }

    public void setPair(int n) {
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

    public void importFixedAmount(int nSub, ArrayList<Person> pSub) {//tien cung la tien co dinh(chia deu cho tat ca thanh vien)
        System.out.println("Fixed expense is the expense that will be equally shared for every member!");
        System.out.println("Enter 'quit' to move on to the next session!");
        System.out.print("Enter fixed expense: ");
        while (true) {
            String fixedExpense = input.nextLine();
            if (fixedExpense.equalsIgnoreCase("quit")) {
                break;
            } else {
                try {
                    double parsedFixedExpense = Double.parseDouble(fixedExpense);
                    sumFixed += parsedFixedExpense;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter quit or a valid number");
                }
            }
        }
        for (int i = 0; i < nSub; i++) {// set tien cung cho tung thanh vien
            pSub.get(i).setCung(sumFixed * 1.0 / nSub);
        }
    }

    public void importFlexibleAmount() {//tien mem la tien cua tung thanh vien phai tra
        System.out.println("Flexible expense is the expense that depends on each member!");
        System.out.println("For example: Only 'Khanh' has motorbike, so only him has flexible expense is 150.000 VND paying for motorbike parking!");
        System.out.println("If member doesn't has any flexible expense, enter 0!");
        System.out.println("Enter 'next' to move on to the next member!");
        System.out.println("Enter flexible expense: ");

        for (int i = 0; i < n; i++) {
            double sum = 0;
            System.out.print("Flexible expense of " + p.get(i).getName() + " : ");
            while (true) {
                String flexibleExpense = input.nextLine();
                if (flexibleExpense.equalsIgnoreCase("next")) {
                    break;
                } else {
                    try {
                        double parsedFlexibleExpense = Double.parseDouble(flexibleExpense);
                        sum += parsedFlexibleExpense;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter next or a valid number");
                    }
                }
            }
            sumFlexible += sum;//tong tien mem
            p.get(i).setMem(sum); //set tien mem cho tung thanh vien
        }
    }

    public void importSharedAmount(int nSub, ArrayList<Person> pSub) {//tien flex la tien can chia deu cho tung thanh vien trong can ho
        double sumflex = 0;
        System.out.println("Enter shared expense: ");
        System.out.println("Shared expense is the expense of each member that he/she spends to contribute to the living activities that need to be shared equally for each member!");
        System.out.println("Enter 'next' to move on to the next member!");
        for (int i = 0; i < nSub; i++) {
            double sum = 0;
            System.out.print("Shared expense of " + pSub.get(i).getName() + ": ");
            while (true) {
                String sharedExpense = input.nextLine();

                if (sharedExpense.equalsIgnoreCase("next")) {
                    break;
                } else {
                    try {
                        double parsedSharedExpense = Double.parseDouble(sharedExpense);
                        sum += parsedSharedExpense;
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
        System.out.println("Admin will responsible for expense paying!");
        System.out.print("Enter Admin's name: ");
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

    public void setIntoDebt(ArrayList<Pair> pairSub) {//set tien cung tien mem cho tung cap ---- thu tu cap nhu sau Person1 Person 2 tienno <=> Person1 no Person2: tienno => neu tien no < 0 => Person2 no Person1: Math.abs(tienno)
        for (int i = 0; i < pairSub.size(); i++) {
            if (pairSub.get(i).getPerson1().isAdmin()) {//neu admin la Person1
                pairSub.get(i).setDebt(-pairSub.get(i).getPerson2().getCung() - pairSub.get(i).getPerson2().getMem() + pairSub.get(i).getDebt());
            } else if (pairSub.get(i).getPerson2().isAdmin()) {//neu admin la Person2
                pairSub.get(i).setDebt(pairSub.get(i).getPerson1().getCung() + pairSub.get(i).getPerson1().getMem() + pairSub.get(i).getDebt());
            }
        }
    }

    public void setP(int nSub, ArrayList<Person> pSub, ArrayList<Person> pPosSub, ArrayList<Person> pNegSub) {//chia 2 mang de tinh toan
        n1 = 0;
        n2 = 0;
        for (int i = 0; i < nSub; i++) {
            if (pSub.get(i).getDiff() > 0) {
                pPosSub.add(pSub.get(i)); //pPos bao gom nhung thanh vien duoc nhan tien
                n1++;
            } else if (pSub.get(i).getDiff() < 0) {
                pNegSub.add(pSub.get(i));//pNeg bao gom nhung thanh vien phai tra tien
                n2++;
            }
        }
        while (n1 != n2) {//truong hop 2 mang chenh lech do so nguoi le
            if (n1 > n2) {
                pNegSub.add(new Person("newname", false, 0, 0, 0, 0));
                n2++;
            } else if (n1 < n2) {
                pPosSub.add(new Person("newname", false, 0, 0, 0, 0));
                n1++;
            }
        }
        Collections.sort(pNegSub, (Person p1, Person p2) -> Double.compare(p2.getDiff(), p1.getDiff()));
        Collections.sort(pPosSub, (Person p1, Person p2) -> Double.compare(p2.getDiff(), p1.getDiff()));
        for (int i = 0; i < n2; i++) {
            System.out.println(pNegSub.get(i).getName() + "-" + pNegSub.get(i).getDiff());
        }
        for (int i = 0; i < n1; i++) {
            System.out.println(pPosSub.get(i).getName() + "-" + pPosSub.get(i).getDiff());
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

    public void calculateTurn() {
        ArrayList<Person> pSub;
        int nSub;
        System.out.println("Calculate Shared Expense!");
        while (true) {
            pSub = new ArrayList<>();
            nSub = 0;
            System.out.print("Enter number of people will be calculated this turn: ");
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
            importFixedAmount(nSub, pSub);
            importFlexibleAmount();
            importSharedAmount(nSub, pSub);
            for (Person psub : pSub) {
                for (int i = 0; i < p.size(); i++) {
                    if (p.get(i).getName().equals(psub.getName())) {
                        p.get(i).setDiff(p.get(i).getDiff() + psub.getDiff());
                        p.get(i).setCung(p.get(i).getCung() + psub.getCung());
                    }
                }
                System.out.print(psub.getName() + "| ");
                System.out.print(psub.getDiff() + "| ");
                System.out.println("\n");
            }

        }
        setIntoDebt(pair);
        setP(n, p, pPos, pNeg);
        calculateExpense(pPos, pNeg, pair);
        result(pair);
    }

    public void calculateExpense(ArrayList<Person> pPosSub, ArrayList<Person> pNegSub, ArrayList<Pair> pairSub) {//tinh tien se cho ra ket qua no giua tat ca cac cap bao gom: tien cung, tien mem, tien flex va tien no
        for (int j = 0; j < n2; j++) {
            for (int i = 0; i < n1; i++) {
                if (pPosSub.get(j).getDiff() <= Math.abs(pNegSub.get(i).getDiff())) {//check neu 1 trong 2 nguoi trong cap dang xet co so tien check lech = 0 se skip
                    if (pPosSub.get(j).getDiff() == 0 || pNegSub.get(i).getDiff() == 0) {
                        continue;
                    }
                    for (int k = 0; k < pairSub.size(); k++) {
                        if (pNegSub.get(i).equals(pairSub.get(k).getPerson1())) {//chieu thuan
                            if (pPosSub.get(j).equals(pairSub.get(k).getPerson2())) {//check cap dang xet = cap trong mang pair
                                pairSub.get(k).setDebt(pPosSub.get(j).getDiff() + pairSub.get(k).getDebt());//set tien no + them tien check lech 
                                break;
                            }
                        } else if (pPosSub.get(j).equals(pairSub.get(k).getPerson1())) {//chieu nguoc 
                            if (pNegSub.get(i).equals(pairSub.get(k).getPerson2())) {//check cap dang xet = cap trong mang pair
                                //pairSub.get(k).setTienno(pbeSub.get(i).getDiff() + pairSub.get(k).getTienno());//set tien no + them tien check lech 
                                pairSub.get(k).setDebt(-pPosSub.get(j).getDiff() + pairSub.get(k).getDebt());//set tien no + them tien check lech 
                                break;
                            }
                        }
                    }
                    pNegSub.get(i).setDiff(pNegSub.get(i).getDiff() + pPosSub.get(j).getDiff());
                    pPosSub.get(j).setDiff(0);
                } else {
                    if (pPosSub.get(j).getDiff() == 0 || pNegSub.get(i).getDiff() == 0) {
                        continue;
                    }
                    for (int k = 0; k < pairSub.size(); k++) {
                        if (pNegSub.get(i).equals(pairSub.get(k).getPerson1())) {//chieu thuan 
                            if (pPosSub.get(j).equals(pairSub.get(k).getPerson2())) {
                                pairSub.get(k).setDebt(Math.abs(pNegSub.get(i).getDiff()) + pairSub.get(k).getDebt());
                                break;
                            }
                        } else if (pPosSub.get(j).equals(pairSub.get(k).getPerson1())) {//chieu nguoc
                            if (pNegSub.get(i).equals(pairSub.get(k).getPerson2())) {
                                pairSub.get(k).setDebt(pNegSub.get(i).getDiff() + pairSub.get(k).getDebt());
                                break;
                            }
                        }
                    }
                    pPosSub.get(j).setDiff(pNegSub.get(i).getDiff() + pPosSub.get(j).getDiff());
                    pNegSub.get(i).setDiff(0);
                }
            }
        }
    }

    public void result(ArrayList<Pair> pairSub) {
        for (int i = 0; i < pairSub.size(); i++) {
            if (pairSub.get(i).getDebt() > 0) {
                System.out.printf("%s will pay %s: %.2f VND",
                        pairSub.get(i).getPerson1().getName(),
                        pairSub.get(i).getPerson2().getName(),
                        pairSub.get(i).getDebt());
                System.out.println("");
            } else if (pairSub.get(i).getDebt() < 0) {
                System.out.printf("%s will pay %s: %.2f VND",
                        pairSub.get(i).getPerson2().getName(),
                        pairSub.get(i).getPerson1().getName(),
                        Math.abs(pairSub.get(i).getDebt()));
                System.out.println("");
            }
        }

        if (sumFlexible > 0) {
            System.out.println("Admin pay for flexible expense: " + sumFlexible + "kVND");
        }

        if (sumFixed > 0) {
            System.out.println("Admin pay for fixed expense: " + sumFixed + "kVND");
        }
    }
}
