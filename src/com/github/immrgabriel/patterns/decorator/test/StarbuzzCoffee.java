package com.github.immrgabriel.patterns.decorator.test;


import com.github.immrgabriel.patterns.decorator.component.Beverage;
import com.github.immrgabriel.patterns.decorator.concreteComponent.DarkRoast;
import com.github.immrgabriel.patterns.decorator.concreteComponent.Espresso;
import com.github.immrgabriel.patterns.decorator.concreteComponent.HouseBlend;
import com.github.immrgabriel.patterns.decorator.decorator.Mocha;
import com.github.immrgabriel.patterns.decorator.decorator.Soy;
import com.github.immrgabriel.patterns.decorator.decorator.Whip;

public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage01 = new Espresso();
        System.out.println(beverage01);

        Beverage beverage02 = new DarkRoast();
        beverage02 = new Mocha(beverage02);
        beverage02 = new Mocha(beverage02);
        beverage02 = new Whip(beverage02);
        System.out.println(beverage02);

        Beverage beverage03 = new HouseBlend();
        beverage03 = new Soy(beverage03);
        beverage03 = new Mocha(beverage03);
        beverage03 = new Whip(beverage03);
        System.out.println(beverage03);
    }
}
