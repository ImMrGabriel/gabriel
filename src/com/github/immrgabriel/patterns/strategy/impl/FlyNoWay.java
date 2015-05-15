package com.github.immrgabriel.patterns.strategy.impl;

import com.github.immrgabriel.patterns.strategy.FlyBehavior;

public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}
