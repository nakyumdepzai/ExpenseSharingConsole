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
    double debt;

    public Pair() {
    }

    public Pair(Person person1, Person person2, double debt) {
        this.person1 = person1;
        this.person2 = person2;
        this.debt = debt;
    }

    public Person getPerson1() {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public double getDebt() {
        return debt;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }



}
