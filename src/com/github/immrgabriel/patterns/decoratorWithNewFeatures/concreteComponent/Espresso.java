package com.github.immrgabriel.patterns.decoratorWithNewFeatures.concreteComponent;

import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Beverage;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Size;

public class Espresso extends Beverage {

    public Espresso(Size size) {
        this.size = size;
        description = "Espresso";
    }

    @Override
    public double cost() {
        if(size == Size.TALL) {
            return 1.80;
        } else if(size == Size.GRANDE) {
            return 1.85;
        } else if(size == Size.VENTI) {
            return 1.99;
        }
        return 1.99;
    }
}
