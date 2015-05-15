package com.github.immrgabriel.patterns.decoratorWithNewFeatures.concreteComponent;

import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Beverage;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Size;

public class Decaf extends Beverage {

    public Decaf(Size size) {
        this.size = size;
        description = "Decaf";
    }

    @Override
    public double cost() {
        if(size == Size.TALL) {
            return .95;
        } else if(size == Size.GRANDE) {
            return 1.00;
        } else if(size == Size.VENTI) {
            return 1.05;
        }
        return 1.05;
    }
}
