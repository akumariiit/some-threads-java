package com.some.threads.synchronisation;

public class Synchronised {

    private int count = 0;

    public static void main(String[] args) {
        Synchronised synchronised = new Synchronised();
        try {
            synchronised.doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void increament() {
        count++;
    }
    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i=0; i<10000; i++) {
                increament();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i=0; i<10000; i++) {
                increament();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count  = " + count);
    }
}
