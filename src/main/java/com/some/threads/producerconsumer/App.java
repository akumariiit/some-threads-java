package com.some.threads.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    private static final Random random = new Random();

    public static void producer() throws InterruptedException {
        while (true) {
            queue.put(random.nextInt(100));
        }
    }

    public static void consumer() throws InterruptedException {
        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                final Integer value = queue.take();
                System.out.println("Taken value " + value + " queue size = " + queue.size());
            }
        }
    }

    public static void main(String[] args) throws  InterruptedException {
        Thread producerThread = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
}
