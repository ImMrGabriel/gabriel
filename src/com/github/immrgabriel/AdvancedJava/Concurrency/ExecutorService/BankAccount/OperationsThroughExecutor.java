package com.github.immrgabriel.AdvancedJava.Concurrency.ExecutorService.BankAccount;

import com.github.immrgabriel.AdvancedJava.Concurrency.DeadLock.BankAccount.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class OperationsThroughExecutor {
    public static void main(String[] args) {
        final Account a = new Account(1000, new ReentrantLock());
        final Account b = new Account(2000, new ReentrantLock());
        Random rnd = new Random();
        ScheduledExecutorService monitoringFailures = Executors.newSingleThreadScheduledExecutor();
        monitoringFailures.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date(System.currentTimeMillis()) + " was " + a.getFailCounter() + " fails");
            }
        }, 0, 1, TimeUnit.SECONDS);                                 // каждую секунду выводит кол-во неудачных трансферов
        ExecutorService service = Executors.newFixedThreadPool(3);
        List<Future<Boolean>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(service.submit(new Transfer(a, b, rnd.nextInt(400), i)));
        }
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);          //Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted
        } catch (InterruptedException e) {e.printStackTrace();}
//        for(Future<Boolean> f : futures) {
        for (int i = 0; i < futures.size(); i++) {
            Future<Boolean> f = futures.get(i);
            if(f.isDone()) {                                        // возвращает false, если вычисление продолжается, и true – если оно завершено.
                try {
                    System.out.println("#" + i + " get " + f.get());                    // устанавливает блокировку до тех пор, пока не будет готов результат.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        monitoringFailures.shutdown();
    }
}
