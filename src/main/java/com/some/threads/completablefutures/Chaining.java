package com.some.threads.completablefutures;

import java.util.concurrent.CompletableFuture;

public class Chaining {

    public static CompletableFuture<Integer> create() {
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
        cf.thenApply(data -> data * 2)
                .thenApply(data -> data + 1)
                .thenAccept(data -> printIt(data))
                .thenRun(() -> System.out.println("in run"))
                .thenRun(() -> System.out.println("in run second time"));
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
