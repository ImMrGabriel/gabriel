package com.github.immrgabriel.patterns.factory.abstractFactory.client.impl;

import com.github.immrgabriel.patterns.factory.abstractFactory.client.Pizza;
import com.github.immrgabriel.patterns.factory.abstractFactory.abstractFactory.PizzaIngredientFactory;

public class VeggiePizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
 
	public VeggiePizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}
 
	public void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
		veggies = ingredientFactory.createVeggies();
	}
}
