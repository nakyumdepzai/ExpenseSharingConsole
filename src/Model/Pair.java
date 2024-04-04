/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author thaiq
 */
public class Pair {

    Person person1;
    Person person2;
    double tienno;

    public Pair() {
    }

    public Pair(Person person1, Person person2, double tienno) {
        this.person1 = person1;
        this.person2 = person2;
        this.tienno = tienno;
    }

    public Person getPerson1() {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public double getTienno() {
        return tienno;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    public void setTienno(double tienno) {
        this.tienno = tienno;
    }



}
