package com.github.immrgabriel.patterns.decorator.decorator;

import com.github.immrgabriel.patterns.decorator.component.Beverage;
import com.github.immrgabriel.patterns.decorator.concreteDecorator.CondimentDecorator;
import com.sun.istack.internal.NotNull;

public class Milk extends CondimentDecorator {
    private Beverage beverage;

    public Milk(@NotNull Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
}
