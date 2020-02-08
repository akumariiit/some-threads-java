package com.some.threads.start;

public class StartingThread {
    public static void main(String[] args) {
        Thread thread1 = new Runner();
        thread1.start();

        Thread thread2 = new Runner();
        thread2.start();

        Thread thread3 = new Thread(new Runner2());
        thread3.start();

        Thread thread4 = new Thread(new Runner2());
        thread4.start();
    }

}

class Runner extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                System.out.println("Loop at " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Runner2 implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
                System.out.println("Loop at " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
