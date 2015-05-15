package com.github.immrgabriel.patterns.simpleFactory.test;

import com.github.immrgabriel.patterns.simpleFactory.client.PizzaStore;
import com.github.immrgabriel.patterns.simpleFactory.entity.Pizza;
import com.github.immrgabriel.patterns.simpleFactory.factory.SimplePizzaFactory;

public class SimpleFactoryDemo {
    public static void main(String[] args) {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        PizzaStore store = new PizzaStore(factory);

        Pizza pepperoni = store.orderPizza("pepperoni");
    }
}
