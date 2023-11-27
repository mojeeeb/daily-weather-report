package de.deefinity.Weather.modell;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    private Double temp;
    private Double temp_min;
    private Double temp_max;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

}