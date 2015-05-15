package com.github.immrgabriel.patterns.decorator.decorator;

import com.github.immrgabriel.patterns.decorator.component.Beverage;
import com.github.immrgabriel.patterns.decorator.concreteDecorator.CondimentDecorator;
import com.sun.istack.internal.NotNull;

public class Soy extends CondimentDecorator {
    private Beverage beverage;

    public Soy(@NotNull Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return .15 + beverage.cost();
    }
}
