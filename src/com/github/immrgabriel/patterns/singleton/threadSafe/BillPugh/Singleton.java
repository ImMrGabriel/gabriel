package com.github.immrgabriel.patterns.singleton.threadSafe.BillPugh;

public class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private final static Singleton INSTANCE = new Singleton();
    }
}
