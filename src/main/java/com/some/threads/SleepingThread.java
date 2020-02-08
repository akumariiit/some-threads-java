package com.some.threads;

public class SleepingThread implements Runnable     {
    public static void main(String[] args) {
        Thread myRunnable = new Thread(new MyRunnabel(), "MyRunnableSleeper");
        myRunnable.start();
    }
    @Override
    public void run() {

        while (true) {
            printSomething();
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void printSomething() {
        System.out.println(Thread.currentThread().getName() +" is executing");
        System.out.println("I am printing");
    }
}
