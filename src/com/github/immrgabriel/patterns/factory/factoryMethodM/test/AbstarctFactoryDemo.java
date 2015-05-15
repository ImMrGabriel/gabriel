package com.github.immrgabriel.patterns.factory.factoryMethodM.test;

import com.github.immrgabriel.patterns.factory.factoryMethodM.creator.PizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethodM.creator.impl.CaliforniaPizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethodM.creator.impl.ChicagoPizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethodM.creator.impl.NYPizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethodM.entity.Pizza;

public class AbstarctFactoryDemo {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();
        PizzaStore californiaStore = new CaliforniaPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println(pizza.getName() + " is ready!");
        pizza = chicagoStore.orderPizza("cheese");
        System.out.println(pizza.getName() + " is ready!");
        pizza = californiaStore.orderPizza("cheese");
        System.out.println(pizza.getName() + " is ready!");

    }
}
