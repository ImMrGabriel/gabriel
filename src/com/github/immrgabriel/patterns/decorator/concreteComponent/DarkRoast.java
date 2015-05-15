package com.github.immrgabriel.patterns.decorator.concreteComponent;

import com.github.immrgabriel.patterns.decorator.component.Beverage;

public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "Dark Roast Coffee";
    }

    @Override
    public double cost() {
        return .99;
    }
}
