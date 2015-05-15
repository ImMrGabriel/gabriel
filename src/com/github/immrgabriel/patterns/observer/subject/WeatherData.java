package com.github.immrgabriel.patterns.observer.subject;

import com.github.immrgabriel.patterns.observer.Observer;
import com.github.immrgabriel.patterns.observer.Subject;
import com.github.immrgabriel.patterns.observer.features.Data;

import java.util.LinkedList;
import java.util.List;

public class WeatherData implements Subject {

    private float temperature;
    private float humidity;
    private float pressure;
    private List<Observer> observers;

    public WeatherData() {
        observers = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        Data data = new Data(temperature, humidity, pressure);
        for(Observer observer : observers) {
            observer.update(data);
        }
    }

    public void measurementsChanged() {
        notifyObserver();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
