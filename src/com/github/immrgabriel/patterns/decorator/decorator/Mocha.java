package com.github.immrgabriel.patterns.decorator.decorator;

import com.github.immrgabriel.patterns.decorator.component.Beverage;
import com.github.immrgabriel.patterns.decorator.concreteDecorator.CondimentDecorator;
import com.sun.istack.internal.NotNull;

public class Mocha extends CondimentDecorator {
    private Beverage beverage;

    public Mocha(@NotNull Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}
