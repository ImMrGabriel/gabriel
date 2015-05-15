package com.github.immrgabriel.AdvancedJava.Concurrency.Synchronizers;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        final CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.err.println("                                       All finished!");
                pool.shutdown();
            }
        });
        for (int i = 0; i < 10; i++) {
            pool.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    System.err.println(Thread.currentThread().getName() + ", barriers are " + barrier.getNumberWaiting() + " of " + barrier.getParties());
                    barrier.await();
                    System.err.println("start  " + Thread.currentThread().getName());
//                    Thread.sleep(300 + (int) (Math.random() * 100));
                    System.err.println("       " + Thread.currentThread().getName() + " finish");
                    return null;
                }
            });
        }

    }
}
