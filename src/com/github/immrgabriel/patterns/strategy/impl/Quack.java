package com.github.immrgabriel.patterns.strategy.impl;

import com.github.immrgabriel.patterns.strategy.QuackBehavior;

public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
