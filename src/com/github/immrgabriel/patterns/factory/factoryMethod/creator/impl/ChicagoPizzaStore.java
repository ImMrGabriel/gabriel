package com.github.immrgabriel.patterns.factory.factoryMethod.creator.impl;

import com.github.immrgabriel.patterns.factory.factoryMethod.creator.PizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.Pizza;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.impl.chicago.ChicagoStyleCheesePizza;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.impl.chicago.ChicagoStyleClamPizza;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.impl.chicago.ChicagoStylePepperoniPizza;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.impl.chicago.ChicagoStyleVeggiePizza;


public class ChicagoPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;

        if("cheese".equals(type)) {
            pizza = new ChicagoStyleCheesePizza();
        } else if("pepperoni".equals(type)) {
            pizza = new ChicagoStylePepperoniPizza();
        } else if("clam".equals(type)) {
            pizza = new ChicagoStyleClamPizza();
        } else if("veggie".equals(type)) {
            pizza = new ChicagoStyleVeggiePizza();
        }
        return pizza;
    }
}
