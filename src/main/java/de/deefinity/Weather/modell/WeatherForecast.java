package de.deefinity.Weather.modell;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {
    private List<WeatherData> list;

    public List<WeatherData> getList() {
        return list;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }
}