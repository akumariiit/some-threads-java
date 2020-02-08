package com.some.threads;

public class InterruptingThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while sleeping : " + Thread.currentThread().getName());
            e.printStackTrace();
        }
        while (!Thread.interrupted()) {

        }
        System.out.println(Thread.currentThread().getName() + " Interrupted again");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new InterruptingThread(), "My Interrupting thread");
        thread.start();

        System.out.println("Sleeping in main thread " + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("Interrupting thread " + Thread.currentThread().getName());
        thread.interrupt();

        System.out.println("Sleeping in main thread " + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("Interrupting thread " + Thread.currentThread().getName());
        thread.interrupt();
    }
}
