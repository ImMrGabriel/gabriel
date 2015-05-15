package com.github.immrgabriel.AdvancedJava.Concurrency.Synchronizers;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            pool.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    latch.countDown();
                    System.err.println("wait   " + Thread.currentThread().getName());
                    latch.await();
                    System.err.println("start  " + Thread.currentThread().getName());
//                    Thread.sleep(300 + (int) (Math.random() * 100));
                    System.err.println("finish " + Thread.currentThread().getName());
                    return null;
                }
            });
        }

    }
}
