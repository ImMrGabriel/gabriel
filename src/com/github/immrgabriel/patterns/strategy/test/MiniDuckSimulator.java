package com.github.immrgabriel.patterns.strategy.test;

import com.github.immrgabriel.patterns.strategy.Duck;
import com.github.immrgabriel.patterns.strategy.impl.FlyRocketPowered;
import com.github.immrgabriel.patterns.strategy.impl.MallardDuck;
import com.github.immrgabriel.patterns.strategy.impl.ModelDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallardDuck = new MallardDuck();
        mallardDuck.display();
        mallardDuck.performFly();
        mallardDuck.performQuack();

        System.out.println();

        Duck modelDuck = new ModelDuck();
        modelDuck.display();
        modelDuck.performFly();
        modelDuck.setFlyBehavior(new FlyRocketPowered());
        modelDuck.performFly();
    }
}
