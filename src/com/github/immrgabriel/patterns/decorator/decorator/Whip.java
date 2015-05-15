package com.github.immrgabriel.patterns.decorator.decorator;

import com.github.immrgabriel.patterns.decorator.component.Beverage;
import com.github.immrgabriel.patterns.decorator.concreteDecorator.CondimentDecorator;
import com.sun.istack.internal.NotNull;

public class Whip extends CondimentDecorator {
    private Beverage beverage;

    public Whip(@NotNull Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return .10 + beverage.cost();
    }
}
