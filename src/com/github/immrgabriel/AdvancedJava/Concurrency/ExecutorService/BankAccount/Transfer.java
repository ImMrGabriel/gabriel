package com.github.immrgabriel.AdvancedJava.Concurrency.ExecutorService.BankAccount;

import com.github.immrgabriel.AdvancedJava.Concurrency.DeadLock.BankAccount.Account;
import com.github.immrgabriel.AdvancedJava.Concurrency.DeadLock.BankAccount.InsufficientFundsExcepion;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Transfer implements Callable<Boolean> {
    private final Account accountFrom;
    private final Account accountTo;
    private final int amount;
    private int id;

    public Transfer(Account accountFrom, Account accountTo, int amount, int id) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.id = id;
    }

    @Override
    public Boolean call() throws Exception {
        if(accountFrom.getBalance() < amount)
            throw new InsufficientFundsExcepion();
        Lock lock1 = accountFrom.getLock();
        Lock lock2 = accountTo.getLock();
        if(lock1.tryLock(3, TimeUnit.SECONDS)) {
            try {
                if (lock2.tryLock(3, TimeUnit.SECONDS)) {
                    try {
                        accountFrom.withdraw(amount);
                        try {
                            long tm = new Random().nextInt(5000);
                            System.out.println("#" + id + " works " + tm + " ms");
                            Thread.sleep(tm);
                        } catch (InterruptedException e) {e.printStackTrace();}
                        accountTo.deposit(amount);
                        System.out.println("#" + id + " Success transfer acc1 (" + amount + ") -> acc2 => " + "acc1{" + accountFrom.getBalance() + "}, acc2{" + accountTo.getBalance() + "}");
                        return true;
                    } finally {
                        lock2.unlock();
                    }
                } else {
                    System.out.println("#" + id + " Not success! lock 2 in " + Thread.currentThread().getName());
                    accountFrom.incFailedCounter();
                    accountTo.incFailedCounter();
                    return false;
                }
            } finally {
                lock1.unlock();
            }
        } else {
            System.out.println("#" + id + " Not success! lock 1 in " + Thread.currentThread().getName());
            accountFrom.incFailedCounter();
            accountTo.incFailedCounter();
            return false;
        }
    }
}
