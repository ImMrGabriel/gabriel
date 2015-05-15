package com.github.immrgabriel.patterns.factory.factoryMethod.entity.base;

import com.github.immrgabriel.patterns.factory.factoryMethod.entity.Pizza;

public abstract class BasePizza implements Pizza {

    @Override
    public void box() {
        System.out.println("boxing " + this.getClass().getSimpleName());
    }

    @Override
    public void cut() {
        System.out.println("cutting " + this.getClass().getSimpleName());
    }

    @Override
    public void bake() {
        System.out.println("baking " + this.getClass().getSimpleName());
    }

    @Override
    public void prepare() {
        System.out.println("preparing " + this.getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return "    *** " + this.getClass().getSimpleName() + " is ready! ***";
    }
}
