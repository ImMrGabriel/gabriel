package com.github.immrgabriel.patterns.observerJavaUtil.test;

import com.github.immrgabriel.patterns.observerJavaUtil.observers.CurrentConditionsDisplay;
import com.github.immrgabriel.patterns.observerJavaUtil.observers.ForecastDisplay;
import com.github.immrgabriel.patterns.observerJavaUtil.observers.HeatIndexDisplay;
import com.github.immrgabriel.patterns.observerJavaUtil.observers.StatisticsDisplay;
import com.github.immrgabriel.patterns.observerJavaUtil.subject.WeatherData;

import java.util.Observer;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Observer currentDisplay = new CurrentConditionsDisplay(weatherData);
        Observer statisticsDisplay = new StatisticsDisplay(weatherData);
        Observer forecastDisplay = new ForecastDisplay(weatherData);
        Observer heatIndexDisplay = new HeatIndexDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
	
}
