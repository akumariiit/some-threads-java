package com.some.threads.reenterant;


import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {
    private int count = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition cond = lock.newCondition();

    public void firstThread () throws InterruptedException {
        lock.lock();

        System.out.println("Waiting");
        cond.await();
        System.out.println("Resume first thread");
        try {
            increament();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Please press enter");
        new Scanner(System.in).nextLine();
        System.out.println("Got enter");
        cond.signal();
        try {
            increament();
        } finally {
            lock.unlock();
        }
    }

    public void increament() {
        for (int i=0; i<10000; i++) {
            count++;
        }
    }

    public void finish() {
        System.out.println("Counnt = " + count);

    }
}
