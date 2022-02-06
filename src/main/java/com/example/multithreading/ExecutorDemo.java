package com.example.multithreading;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    public static void main(String[] args) {
        int count=0;
        Thread t = new Thread(new Task());
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0; i<100; i++){
            executor.execute(t);
        }
        executor.shutdown();
        System.out.println("Finished thread:"+Thread.currentThread().getName());

    }
}

class Task implements Runnable {
    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    private int count;

    @Override
    public void run() {
        threadLocal.set(++count);
        int wait = new Random().nextInt(5);
        try {
            Thread.sleep(wait*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Inside thread: "+Thread.currentThread().getName() + " count: "+ threadLocal.get());
    }
}
