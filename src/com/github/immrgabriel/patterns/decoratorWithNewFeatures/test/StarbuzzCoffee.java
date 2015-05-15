package com.github.immrgabriel.patterns.decoratorWithNewFeatures.test;


import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Beverage;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Size;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.concreteComponent.DarkRoast;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.concreteComponent.HouseBlend;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.decorator.Soy;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.decorator.Whip;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.concreteComponent.Espresso;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.decorator.Mocha;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.decorator.Soy;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.decorator.Whip;

public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage01 = new Espresso(Size.GRANDE);
        System.out.println(beverage01);

        Beverage beverage02 = new DarkRoast(Size.TALL);
        beverage02 = new Mocha(beverage02);
        beverage02 = new Mocha(beverage02);
        beverage02 = new Whip(beverage02);
        System.out.println(beverage02);

        Beverage beverage03 = new HouseBlend(Size.VENTI);
        beverage03 = new Soy(beverage03);
        beverage03 = new Mocha(beverage03);
        beverage03 = new Whip(beverage03);
        System.out.println(beverage03);
    }
}
