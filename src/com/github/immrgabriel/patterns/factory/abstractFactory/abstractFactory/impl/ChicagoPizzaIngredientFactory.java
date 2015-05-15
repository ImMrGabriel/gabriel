package com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory.impl;

import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.cheese.Cheese;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.cheese.impl.MozzarellaCheese;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.clams.Clams;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.clams.impl.FrozenClams;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.dough.Dough;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.dough.impl.ThickCrustDough;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.pepperoni.Pepperoni;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.pepperoni.impl.SlicedPepperoni;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.sauce.Sauce;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.sauce.impl.PlumTomatoSauce;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.Veggies;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.impl.BlackOlives;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.impl.Eggplant;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.ingradient.veggies.impl.Spinach;
import com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory.PizzaIngredientFactory;

public class ChicagoPizzaIngredientFactory
	implements PizzaIngredientFactory
{

	public Dough createDough() {
		return new ThickCrustDough();
	}

	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	public Cheese createCheese() {
		return new MozzarellaCheese();
	}

	public Veggies[] createVeggies() {
		Veggies veggies[] = { new BlackOlives(),
		                      new Spinach(),
		                      new Eggplant() };
		return veggies;
	}

	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FrozenClams();
	}
}
