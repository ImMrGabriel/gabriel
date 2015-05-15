package com.github.immrgabriel.patterns.factory.simpleFactory.test;

import com.github.immrgabriel.patterns.factory.simpleFactory.client.PizzaStore;
import com.github.immrgabriel.patterns.factory.simpleFactory.entity.Pizza;
import com.github.immrgabriel.patterns.factory.simpleFactory.factory.SimplePizzaFactory;

public class SimpleFactoryDemo {
    public static void main(String[] args) {
        SimplePizzaFactory factory = new SimplePizzaFactory();
        PizzaStore store = new PizzaStore(factory);

        Pizza pepperoni = store.orderPizza("pepperoni");
    }
}
