package com.github.immrgabriel.patterns.factory.factoryMethodM.creator;

import com.github.immrgabriel.patterns.factory.factoryMethodM.entity.Pizza;

public abstract class PizzaStore {

    public Pizza orderPizza(String type) {

        Pizza pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}
