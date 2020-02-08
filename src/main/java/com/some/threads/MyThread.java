package com.some.threads;

public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread("My_Thread");
        myThread.start();
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " Thread Running");
    }
}
