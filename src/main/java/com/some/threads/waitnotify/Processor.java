package com.some.threads.waitnotify;


import java.util.Scanner;

public class Processor {

    public void produce () throws InterruptedException {
        synchronized (this) {
            System.out.println("In producer waiting");
            wait();
            System.out.println("Resumed");
        }

    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Please press enter");
            scanner.nextLine();
            System.out.println("After enter");
            notify();
            Thread.sleep(3000);
        }
    }

    public static void main(String[] args) {

    }
}
