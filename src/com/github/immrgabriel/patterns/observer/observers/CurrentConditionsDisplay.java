package com.github.immrgabriel.patterns.observer.observers;

import com.github.immrgabriel.patterns.observer.Observer;
import com.github.immrgabriel.patterns.observer.features.Data;
import com.github.immrgabriel.patterns.observer.features.DisplayElement;
import com.github.immrgabriel.patterns.observer.subject.WeatherData;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(Data updateData) {
        temperature = updateData.getTemperature();
        humidity = updateData.getHumidity();
        display();
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
	
}
