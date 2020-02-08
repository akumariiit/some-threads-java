package com.some.threads.reenterant;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();

        Thread t1 = new Thread(() -> {
            try {
                processor.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        processor.finish();
    }
}
