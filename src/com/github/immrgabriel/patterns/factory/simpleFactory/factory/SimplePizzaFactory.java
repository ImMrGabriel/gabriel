package com.github.immrgabriel.patterns.factory.simpleFactory.factory;

import com.github.immrgabriel.patterns.factory.simpleFactory.entity.Pizza;
import com.github.immrgabriel.patterns.factory.simpleFactory.impl.CheesePizza;
import com.github.immrgabriel.patterns.factory.simpleFactory.impl.ClamPizza;
import com.github.immrgabriel.patterns.factory.simpleFactory.impl.PepperoniPizza;
import com.github.immrgabriel.patterns.factory.simpleFactory.impl.VeggiePizza;

public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if("cheese".equals(type)) {
            pizza = new CheesePizza();
        } else if("pepperoni".equals(type)) {
            pizza = new PepperoniPizza();
        } else if("clam".equals(type)) {
            pizza = new ClamPizza();
        } else if("veggie".equals(type)) {
            pizza = new VeggiePizza();
        }
        return pizza;
    }

}
