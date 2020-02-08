package com.some.threads.deadlock;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {
    Account a1 = new Account();
    Account a2 = new Account();
    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();
    Random rand = new Random();

    public void firstThread () throws InterruptedException {

        for (int i=0; i<1000; i++) {
            lock1.lock();
            lock2.lock();
            try {
                Account.transfer(a1, a2, rand.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }

    }

    public void secondThread() throws InterruptedException {
        for (int i=0; i<1001; i++) {
            // dead lock since order is not same
            lock1.lock();
            lock2.lock();
            try {
                Account.transfer(a2, a1, rand.nextInt(100));
            } finally {
                lock2.unlock();
                lock1.unlock();
            }
        }

    }

    private void tryAcquireLock(Lock lock1, Lock lock2) throws InterruptedException {
        while (true) {
            boolean lock1Done = false;
            boolean lock2Done = false;
            try {
                lock1Done = lock1.tryLock();
                lock2Done = lock2.tryLock();
            }
            finally {
                if (lock1Done && lock2Done) {
                    return;
                }
                if (lock1Done) {
                    lock1.unlock();
                }
                if (lock2Done) {
                    lock2.unlock();
                }
            }
            Thread.sleep(1);
        }
    }

    public void finish() {
        System.out.println("account 1 = " + a1.getAmount());
        System.out.println("account 2 = " + a2.getAmount());
        System.out.println("Total = " + (a1.getAmount() + a2.getAmount()));

    }
}
