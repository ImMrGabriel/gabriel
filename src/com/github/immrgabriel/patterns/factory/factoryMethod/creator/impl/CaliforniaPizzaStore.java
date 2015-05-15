package com.github.immrgabriel.patterns.factory.factoryMethod.creator.impl;

import com.github.immrgabriel.patterns.factory.factoryMethod.creator.PizzaStore;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.Pizza;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.impl.california.CaliforniaStyleCheesePizza;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.impl.california.CaliforniaStyleClamPizza;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.impl.california.CaliforniaStylePepperoniPizza;
import com.github.immrgabriel.patterns.factory.factoryMethod.entity.impl.california.CaliforniaStyleVeggiePizza;


public class CaliforniaPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;

        if("cheese".equals(type)) {
            pizza = new CaliforniaStyleCheesePizza();
        } else if("pepperoni".equals(type)) {
            pizza = new CaliforniaStylePepperoniPizza();
        } else if("clam".equals(type)) {
            pizza = new CaliforniaStyleClamPizza();
        } else if("veggie".equals(type)) {
            pizza = new CaliforniaStyleVeggiePizza();
        }
        return pizza;
    }
}
