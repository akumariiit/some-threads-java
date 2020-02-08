package com.some.threads;

import java.util.Random;

public class JoinThread implements Runnable {
    @Override
    public void run() {
        Random random = new Random(Integer.MAX_VALUE);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            random.nextInt();
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("Thread %s finished in %d ms", Thread.currentThread().getName(), (end - start)));
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new JoinThread(), "" + i);
            threads[i].start();
        }
        for (int i = 0; i < 5; i++) {
            threads[i].join();
        }
        System.out.println("All threads are finished");
    }
}
