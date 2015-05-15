package com.github.immrgabriel.patterns.factory.factoryMethodM.entity.impl.chicago;


import com.github.immrgabriel.patterns.factory.factoryMethodM.entity.Pizza;

public class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        dough = "Plum Tomato Sauce";

        toppings.add("Shredded Mozarella Cheese");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
