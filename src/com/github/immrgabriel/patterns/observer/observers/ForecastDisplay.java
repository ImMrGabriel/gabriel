package com.github.immrgabriel.patterns.observer.observers;

import com.github.immrgabriel.patterns.observer.Observer;
import com.github.immrgabriel.patterns.observer.features.Data;
import com.github.immrgabriel.patterns.observer.features.DisplayElement;
import com.github.immrgabriel.patterns.observer.subject.WeatherData;

public class ForecastDisplay implements Observer, DisplayElement {

    private float currentPressure = 29.92f;
    private float lastPressure;
    private WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(Data updateData) {
        lastPressure = currentPressure;
        currentPressure = updateData.getPressure();
        display();
    }

    @Override
    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same");
        } else if (currentPressure < lastPressure) {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }
	
}
