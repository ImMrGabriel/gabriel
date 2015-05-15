package com.github.immrgabriel.AdvancedJava.Concurrency.DeadLock.BankAccount;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class Account {

    private int balance;

    private Lock lock;

//    private int failCounter;    //count of failed transfers

    private AtomicInteger failCounter = new AtomicInteger(0);    //count of failed transfers

//    public Account(int balance) {
//        this.balance = balance;
//    }

    public Account(int balance, Lock lock) {
        this.balance = balance;
        this.lock = lock;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }

    public void incFailedCounter() {
//        failCounter++;
        failCounter.incrementAndGet();
    }

    public int getFailCounter() {
        return failCounter.get();
    }
}
