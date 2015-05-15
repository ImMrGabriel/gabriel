package com.github.immrgabriel.patterns.strategy.impl;

import com.github.immrgabriel.patterns.strategy.QuackBehavior;

public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
