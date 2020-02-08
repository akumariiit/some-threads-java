package com.some.threads.completablefutures;

import java.util.concurrent.CompletableFuture;

/**
 * https://www.youtube.com/watch?v=9ueIL0SwEWI&t=937s
 */
public class CompletableFutureExample {
    public static CompletableFuture<Integer> create() {
        System.out.println("create thread: " + Thread.currentThread());
        return CompletableFuture.supplyAsync(() -> compute());
    }

    private static Integer compute() {
        System.out.println("compute thread: " + Thread.currentThread());
        return 2;
    }

    private static void printIt(int value) {
        System.out.println("value: " + value);
        System.out.println("print thread: " + Thread.currentThread());
    }

    public static void main(String[] args) {
        System.out.println("main: " + Thread.currentThread());
        CompletableFuture<Integer> cf = create();
        sleep(1000);
        cf.thenAccept(data -> printIt(data));
        sleep(1000);
    }

    private static boolean sleep(int i) {
        try {
            Thread.sleep(i);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
