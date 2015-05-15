package com.github.immrgabriel.patterns.decoratorWithNewFeatures.concreteComponent;

import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Beverage;
import com.github.immrgabriel.patterns.decoratorWithNewFeatures.component.Size;

public class HouseBlend extends Beverage {

    public HouseBlend(Size size) {
        this.size = size;
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        if(size == Size.TALL) {
            return .77;
        } else if(size == Size.GRANDE) {
            return .83;
        } else if(size == Size.VENTI) {
            return .89;
        }
        return .89;
    }
}
