package com.some.threads.synchronisation;

import java.util.Scanner;

public class SharedVariable {

    public static void main(String[] args) {
        Processor thread = new Processor();
        thread.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        thread.shutDown(false);
    }
}

class Processor extends Thread {
    // volatile will not cache this variable
    private volatile boolean running = true;
    public void run() {
        while(running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown(boolean running) {
        this.running = running;
    }
}
