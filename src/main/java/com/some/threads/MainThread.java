package com.some.threads;

public class MainThread {
    public static void main(String[] args) {
        final long id = Thread.currentThread().getId();
        final String name = Thread.currentThread().getName();
        final int priority = Thread.currentThread().getPriority();
        final Thread.State state = Thread.currentThread().getState();
        final String threadGroupName = Thread.currentThread().getThreadGroup().getName();
        System.out.println("id="+id+"; name="+name+"; priority="+priority+"; state="+state+"; threadGroupName="+threadGroupName);
    }
}
