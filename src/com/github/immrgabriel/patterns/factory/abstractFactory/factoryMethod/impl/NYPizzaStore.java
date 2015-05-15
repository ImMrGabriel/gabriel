package com.github.immrgabriel.patterns.factory.abstractFactory.factoryMethod.impl;

import com.github.immrgabriel.patterns.factory.abstractFactory.client.impl.ClamPizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.impl.PepperoniPizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.impl.VeggiePizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.Pizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.factoryMethod.PizzaStore;
import com.github.immrgabriel.patterns.factory.abstractFactory.client.impl.CheesePizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory.impl.NYPizzaIngredientFactory;
import com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory.PizzaIngredientFactory;

public class NYPizzaStore extends PizzaStore {
 
	protected Pizza createPizza(String item) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory =
			new NYPizzaIngredientFactory();
 
		if (item.equals("cheese")) {
  
			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("New York Style Cheese Pizza");
  
		} else if (item.equals("veggie")) {
 
			pizza = new VeggiePizza(ingredientFactory);
			pizza.setName("New York Style Veggie Pizza");
 
		} else if (item.equals("clam")) {
 
			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("New York Style Clam Pizza");
 
		} else if (item.equals("pepperoni")) {

			pizza = new PepperoniPizza(ingredientFactory);
			pizza.setName("New York Style Pepperoni Pizza");
 
		} 
		return pizza;
	}
}
