package com.some.threads.waitnotify;

import java.util.LinkedList;
import java.util.Random;

public class ProdConsume {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();
    private Random random = new Random();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }

    }


    public void consume() throws InterruptedException {

        while (true) {
            synchronized (lock) {
                System.out.println("List size = " + list.size());
                while (list.size() == 0) {
                    lock.wait();
                }
                final int value = list.removeFirst();
                System.out.println("Value = " + value);
                lock.notify();
            }
            Thread.sleep(1000);
        }


    }
}
