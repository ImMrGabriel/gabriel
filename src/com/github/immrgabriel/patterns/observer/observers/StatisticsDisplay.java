package com.github.immrgabriel.patterns.observer.observers;

import com.github.immrgabriel.patterns.observer.Observer;
import com.github.immrgabriel.patterns.observer.features.Data;
import com.github.immrgabriel.patterns.observer.features.DisplayElement;
import com.github.immrgabriel.patterns.observer.subject.WeatherData;

public class StatisticsDisplay implements Observer, DisplayElement {

    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum= 0.0f;
    private int numReadings;
    private WeatherData weatherData;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(Data updateData) {
        float temperature = updateData.getTemperature();
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

    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
	
}
