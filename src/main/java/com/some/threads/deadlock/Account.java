package com.some.threads.deadlock;

public class Account {
    private int amount  = 10000;

    public void add(int i) {
        amount = amount + i;
    }

    public void remove(int i) {
        amount = amount - i;
    }

    public int getAmount() {
        return amount;
    }

    public static void transfer(Account a1, Account a2, int amount) {
        a1.remove(amount);
        a2.add(amount);
    }
}
