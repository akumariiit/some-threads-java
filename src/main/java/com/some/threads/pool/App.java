package com.some.threads.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

    private final int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Staring : " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed : " + id);

    }
}
public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i=0; i<5; i++) {
            executorService.submit(new Processor(i));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Completed all tasks");

    }
}
