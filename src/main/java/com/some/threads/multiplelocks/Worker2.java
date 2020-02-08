package com.some.threads.multiplelocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * synchronized keyword is one per object
 */
public class Worker2 {

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    Random random = new Random();

    public synchronized void step1() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    public synchronized void step2() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }

    public void process() {
        for (int i=0; i<1000; i++) {
            step1();
            step2();
        }
    }

    public void main() {
        final long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            process();
        });
        Thread t2 = new Thread(() -> {
            process();
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final long end = System.currentTimeMillis();
        System.out.println(String.format("Time taken %d, size of list1 %d, size of list2 %d", (end-start), list1.size(), list2.size()));
    }
}
