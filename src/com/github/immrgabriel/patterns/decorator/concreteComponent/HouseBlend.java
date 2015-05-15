package com.github.immrgabriel.patterns.decorator.concreteComponent;

import com.github.immrgabriel.patterns.decorator.component.Beverage;

public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
