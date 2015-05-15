package com.github.immrgabriel.patterns.observerJavaUtil.observers;

import com.github.immrgabriel.patterns.observerJavaUtil.features.DisplayElement;
import com.github.immrgabriel.patterns.observerJavaUtil.subject.WeatherData;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay implements Observer, DisplayElement {

    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum= 0.0f;
    private int numReadings;

    public StatisticsDisplay(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData) {
            float temperature = ((WeatherData) o).getTemperature();
            tempSum += temperature;
            numReadings++;
            if (temperature > maxTemp) {
                maxTemp = temperature;
            }
            if (temperature < minTemp) {
                minTemp = temperature;
            }
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }

}
