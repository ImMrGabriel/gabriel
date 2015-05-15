package com.github.immrgabriel.patterns.decoratorWithNewFeatures.component;

public abstract class Beverage {
    protected String description = "Unknown Beverage";
    protected Size size;

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    public Size getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Beverage{" + getDescription() + ". Cost: $" + cost() + '}';
    }
}
