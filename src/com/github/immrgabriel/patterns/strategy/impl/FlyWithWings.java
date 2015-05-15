package com.github.immrgabriel.patterns.strategy.impl;

import com.github.immrgabriel.patterns.strategy.FlyBehavior;

public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I'm flying!");
    }
}
