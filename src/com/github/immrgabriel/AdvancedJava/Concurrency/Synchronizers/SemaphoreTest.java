package com.github.immrgabriel.AdvancedJava.Concurrency.Synchronizers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(3);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            pool.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    System.err.println("*" + semaphore.availablePermits());
                    semaphore.acquire();
                    System.err.println("  +" + Thread.currentThread().getName());
                    Thread.sleep(300 + (int)(Math.random() * 100));
                    semaphore.release();
                    System.err.println("*" + semaphore.availablePermits());
                    System.err.println("  -" + Thread.currentThread().getName());
                    return null;
                }
            });
        }

    }
}
