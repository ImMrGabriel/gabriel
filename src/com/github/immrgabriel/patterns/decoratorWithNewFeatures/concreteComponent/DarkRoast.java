package com.github.immrgabriel.patterns.decoratorWithNewFeatures.concreteComponent;

import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Beverage;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Size;

public class DarkRoast extends Beverage {

    public DarkRoast(Size size) {
        this.size = size;
        description = "Dark Roast Coffee";
    }

    @Override
    public double cost() {
        if(size == Size.TALL) {
            return .85;
        } else if(size == Size.GRANDE) {
            return .92;
        } else if(size == Size.VENTI) {
            return .99;
        }
        return .99;
    }
}
