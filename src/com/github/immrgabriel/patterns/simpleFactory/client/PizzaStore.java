package com.github.immrgabriel.patterns.simpleFactory.client;

import com.github.immrgabriel.patterns.simpleFactory.entity.Pizza;
import com.github.immrgabriel.patterns.simpleFactory.factory.SimplePizzaFactory;

public class PizzaStore {

    private SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {

        Pizza pizza = factory.createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }
}
