package com.github.immrgabriel.patterns.factory.abstractFactory.factoryMethod.impl;

import com.github.immrgabriel.patterns.factory.abstractFactory.client.impl.ClamPizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.impl.PepperoniPizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.impl.VeggiePizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.Pizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.factoryMethod.PizzaStore;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.impl.CheesePizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory.impl.ChicagoPizzaIngredientFactory;
import com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory.PizzaIngredientFactory;

public class ChicagoPizzaStore extends PizzaStore {

	protected Pizza createPizza(String item) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory =
		new ChicagoPizzaIngredientFactory();

		if (item.equals("cheese")) {

			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("Chicago Style Cheese Pizza");

		} else if (item.equals("veggie")) {

			pizza = new VeggiePizza(ingredientFactory);
			pizza.setName("Chicago Style Veggie Pizza");

		} else if (item.equals("clam")) {

			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("Chicago Style Clam Pizza");

		} else if (item.equals("pepperoni")) {

			pizza = new PepperoniPizza(ingredientFactory);
			pizza.setName("Chicago Style Pepperoni Pizza");

		}
		return pizza;
	}
}
