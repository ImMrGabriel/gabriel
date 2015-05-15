package com.github.immrgabriel.patterns.decorator.concreteComponent;

import com.github.immrgabriel.patterns.decorator.component.Beverage;

public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
