package com.github.immrgabriel.patterns.decorator.component;

public abstract class Beverage {
    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    @Override
    public String toString() {
        return "Beverage{" + getDescription() + ". Cost: $" + cost() + '}';
    }
}
