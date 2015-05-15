package com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory.impl;

import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.cheese.Cheese;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.cheese.impl.ReggianoCheese;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.clams.Clams;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.clams.impl.FreshClams;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.dough.Dough;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.dough.impl.ThinCrustDough;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.pepperoni.Pepperoni;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.pepperoni.impl.SlicedPepperoni;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.sauce.Sauce;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.sauce.impl.MarinaraSauce;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.Veggies;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.impl.Garlic;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.impl.Mushroom;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.impl.Onion;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.impl.RedPepper;
import com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory.PizzaIngredientFactory;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        Veggies[] veggies = { new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
        return veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClam() {
        return new FreshClams();
    }
}
