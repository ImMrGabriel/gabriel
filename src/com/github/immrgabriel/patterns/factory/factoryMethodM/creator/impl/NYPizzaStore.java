package com.github.immrgabriel.patterns.factory.factoryMethodM.creator.impl;

import com.github.immrgabriel.patterns.factory.factoryMethodM.creator.PizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethodM.entity.Pizza;
import com.github.immrgabriel.patterns.factory.factoryMethodM.entity.impl.ny.NyStyleCheesePizza;
import com.github.immrgabriel.patterns.factory.factoryMethodM.entity.impl.ny.NyStyleClamPizza;
import com.github.immrgabriel.patterns.factory.factoryMethodM.entity.impl.ny.NyStylePepperoniPizza;
import com.github.immrgabriel.patterns.factory.factoryMethodM.entity.impl.ny.NyStyleVeggiePizza;

public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;

        if("cheese".equals(type)) {
            pizza = new NyStyleCheesePizza();
        } else if("pepperoni".equals(type)) {
            pizza = new NyStylePepperoniPizza();
        } else if("clam".equals(type)) {
            pizza = new NyStyleClamPizza();
        } else if("veggie".equals(type)) {
            pizza = new NyStyleVeggiePizza();
        }
        return pizza;
    }
}
