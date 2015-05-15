package com.github.immrgabriel.AdvancedJava.Concurrency.DeadLock.BankAccount;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* jps - get id all run Java process
* jstack id - get thread dump by id process
* kill -3 (Ctrl+Break) - get thread dump in Linux(Windows)
*
* jconsole
* */
public class Operations {
    public static void main(String[] args) {
//        final Account a = new Account(1000);
//        final Account b = new Account(2000);

        final Account a = new Account(1000, new ReentrantLock());
        final Account b = new Account(2000, new ReentrantLock());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transfer(a, b, 500);
                } catch (InsufficientFundsExcepion e) {
                    System.out.println("Insufficient funds");
                }
            }
        }).start();
        try {
            transfer(b, a, 300);
        } catch (InsufficientFundsExcepion e) {
            System.out.println("Insufficient funds");
        }
    }

    public static void transfer(Account acc1, Account acc2, int amount) throws InsufficientFundsExcepion {
//        if(acc1.getBalance() < amount)
//            throw new InsufficientFundsExcepion();
//        acc1.withdraw(amount);
//        acc2.deposit(amount);
//        System.out.println("Success transfer acc1 (" + amount + ") -> acc2 => " + "acc1{" + acc1.getBalance() + "}, acc2{" + acc2.getBalance() + "}");

//        if(acc1.getBalance() < amount)
//            throw new InsufficientFundsExcepion();
//        synchronized (acc1) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {e.printStackTrace();}
//            synchronized (acc2) {
//                acc1.withdraw(amount);
//                acc2.deposit(amount);
//                System.out.println("Success transfer acc1 (" + amount + ") -> acc2 => " + "acc1{" + acc1.getBalance() + "}, acc2{" + acc2.getBalance() + "}");
//            }
//        }

//        if(acc1.getBalance() < amount)
//            throw new InsufficientFundsExcepion();
//        Lock lock1 = acc1.getLock();
//        Lock lock2 = acc2.getLock();
//        if(lock1.tryLock()) {
//            try {
//                try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {e.printStackTrace();}
//                if (lock2.tryLock()) {
//                    try {
//                        acc1.withdraw(amount);
//                        acc2.deposit(amount);
//                        System.out.println("Success transfer acc1 (" + amount + ") -> acc2 => " + "acc1{" + acc1.getBalance() + "}, acc2{" + acc2.getBalance() + "}");
//                    } finally {
//                        lock2.unlock();
//                    }
//                } else {
//                    System.out.println("Not success! lock 2");
//                }
//            } finally {
//                lock1.unlock();
//            }
//        } else {
//            System.out.println("Not success! lock 1");
//        }

        if(acc1.getBalance() < amount)
            throw new InsufficientFundsExcepion();
        Lock lock1 = acc1.getLock();
        Lock lock2 = acc2.getLock();
        if(lock1.tryLock()) {
            try {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {e.printStackTrace();}
                if (lock2.tryLock()) {
                    try {
                        acc1.withdraw(amount);
                        acc2.deposit(amount);
                        System.out.println("Success transfer acc1 (" + amount + ") -> acc2 => " + "acc1{" + acc1.getBalance() + "}, acc2{" + acc2.getBalance() + "}");
                    } finally {
                        lock2.unlock();
                    }
                } else {
                    System.out.println("Not success! lock 2");
                    acc1.incFailedCounter();
                    acc2.incFailedCounter();
                }
            } finally {
                lock1.unlock();
            }
        } else {
            System.out.println("Not success! lock 1");
            acc1.incFailedCounter();
            acc2.incFailedCounter();
        }
    }
}
