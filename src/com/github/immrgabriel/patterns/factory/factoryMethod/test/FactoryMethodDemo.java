package com.github.immrgabriel.patterns.factory.factoryMethod.test;


import com.github.immrgabriel.patterns.factory.factoryMethod.creator.PizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethod.creator.impl.CaliforniaPizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethod.creator.impl.ChicagoPizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethod.creator.impl.NYPizzaStore;

public class FactoryMethodDemo {
    public static void main(String[] args) {

        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();
        PizzaStore californiaStore = new CaliforniaPizzaStore();
        System.out.println(nyStore.orderPizza("veggie"));
        System.out.println(chicagoStore.orderPizza("veggie"));
        System.out.println(californiaStore.orderPizza("veggie"));

    }
}
