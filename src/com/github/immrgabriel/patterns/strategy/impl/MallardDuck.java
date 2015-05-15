package com.github.immrgabriel.patterns.strategy.impl;

import com.github.immrgabriel.patterns.strategy.Duck;

public class MallardDuck extends Duck {

    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a real mallard duck");
    }
}
