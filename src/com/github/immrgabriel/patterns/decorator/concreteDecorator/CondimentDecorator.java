package com.github.immrgabriel.patterns.decorator.concreteDecorator;

import com.github.immrgabriel.patterns.decorator.component.Beverage;

public abstract class CondimentDecorator extends Beverage {

    @Override
    public abstract String getDescription();

}
