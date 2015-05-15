package com.github.immrgabriel.patterns.decorator.concreteComponent;

import com.github.immrgabriel.patterns.decorator.component.Beverage;

public class Decaf extends Beverage {

    public Decaf() {
        description = "Decaf";
    }

    @Override
    public double cost() {
        return 1.05;
    }
}
