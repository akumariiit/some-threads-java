package com.some.threads.countdownlatch;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

    private final CountDownLatch countDownLatch;

    public Processor(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " with latch count " + countDownLatch.getCount());
        countDownLatch.countDown();
        System.out.println(Thread.currentThread().getName() + " with latch count " + countDownLatch.getCount());
        System.out.println("Finished Task");

    }
}
public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        final CountDownLatch countDownLatch = new CountDownLatch(4);

        for (int i=0; i<6; i++) {
            executorService.submit(new Processor(countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Completed Totally with latch count " + countDownLatch.getCount());
        executorService.shutdown();
    }


}
