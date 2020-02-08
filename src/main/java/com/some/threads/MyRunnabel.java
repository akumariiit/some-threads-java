package com.some.threads;

public class MyRunnabel implements Runnable {
    public static void main(String[] args) {
        Thread myRunnable = new Thread(new MyRunnabel(), "MyRunnable");
        myRunnable.start();
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +" is executing");
    }
}
