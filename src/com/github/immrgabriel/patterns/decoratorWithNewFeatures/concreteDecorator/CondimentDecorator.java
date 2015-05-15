package com.github.immrgabriel.patterns.decoratorWithNewFeatures.concreteDecorator;

import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Beverage;

public abstract class CondimentDecorator extends Beverage {

    @Override
    public abstract String getDescription();

}
