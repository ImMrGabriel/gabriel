package com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory;

import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.cheese.Cheese;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.clams.Clams;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.dough.Dough;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.pepperoni.Pepperoni;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.sauce.Sauce;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.Veggies;

public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClam();
}
